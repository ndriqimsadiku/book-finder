package com.bmn.bookfinder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bmn.bookfinder.adapters.BestShareListAdapter
import com.bmn.bookfinder.adapters.BestShareListAdapter.ItemCheckedListClickListener
import com.bmn.bookfinder.adapters.TopPicksAdapter
import com.bmn.bookfinder.adapters.TopicsAdapter
import com.bmn.bookfinder.adapters.TopicsAdapter.OnTopicsSelectChange
import com.bmn.bookfinder.data.room.AppDatabase.Companion.getDatabase
import com.bmn.bookfinder.data.room.BookEntity
import com.bmn.bookfinder.databinding.FragmentHomeBinding
import com.bmn.bookfinder.dummydata.DummyData.firstUseTopics
import com.bmn.bookfinder.helpers.SharedPrefUtils.getFavoriteIds
import com.bmn.bookfinder.models.Topic
import java.util.*

class HomeFragment : Fragment(), TopPicksAdapter.ItemClickListener, OnTopicsSelectChange,
    ItemCheckedListClickListener {
    private var binding: FragmentHomeBinding? = null
    private var selectedTopics: ArrayList<Topic>? = null
    private var topPicksAdapter: TopPicksAdapter? = null
    private var topicsAdapter: TopicsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        selectedTopics = getSelectedTopics()
        initData()
        return binding!!.root
    }

    private fun initData() {
        topicsAdapter = TopicsAdapter(
            context, selectedTopics
        )
        binding!!.topicsRv.adapter = topicsAdapter
        topicsAdapter!!.setClickListener(this)
        binding!!.checkedTopicsBooksRv.isNestedScrollingEnabled = false
        updateSelectedTopics()
    }

    private fun getSelectedTopics(): ArrayList<Topic> {
        val allTopics = firstUseTopics
        val selectedTopics = ArrayList<Topic>()
        val ids = getFavoriteIds(requireContext())
        for (topic in allTopics) {
            if (ids.contains(topic.id)) {
                selectedTopics.add(topic)
            }
        }
        return selectedTopics
    }

    override fun onItemClick(view: View?, id: String?) {
        val action = HomeFragmentDirections.actionHomeFragmentToBookActivity()
        action.bookId = id!!
        Navigation.findNavController(requireView()).navigate(action)
    }

    override fun onUpdate() {
        updateCheckedTopics()
    }

    private fun updateCheckedTopics() {
        val entities = ArrayList<BookEntity?>()
        for ((id) in topicsAdapter!!.getCheckedTopics()) {
            entities.addAll(getDatabase(context).bookDao.getBookByTopicId(id!!.toLong())!!)
        }
        entities.reverse()
        val bestShareListAdapter = BestShareListAdapter(requireContext(), entities)
        bestShareListAdapter.setClickListener(this)
        binding!!.checkedTopicsBooksRv.adapter = bestShareListAdapter
    }

    private fun updateSelectedTopics() {
        val entities = ArrayList<BookEntity?>()
        for ((id) in selectedTopics!!) {
            entities.add(getDatabase(context).bookDao.getCheckedTopic(id!!.toLong()))
        }
        topPicksAdapter = TopPicksAdapter(requireContext(), entities)
        topPicksAdapter!!.setClickListener(this)
        binding!!.topPicksRv.adapter = topPicksAdapter
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}