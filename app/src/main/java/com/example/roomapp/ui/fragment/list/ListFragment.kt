package com.example.roomapp.ui.fragment.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.ui.fragment.add.UserViewModel

class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mListAdapter: ListAdapter
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

        initRecyclerView(view)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {
             mListAdapter.setData(it)
        })

        view.findViewById<View>(R.id.floatingActionButton).setOnClickListener {
            findNavController().navigate(R.id.addFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun initRecyclerView(view: View) {
        mListAdapter = ListAdapter()

        mRecyclerView = view.findViewById(R.id.rvUsers)
        mRecyclerView.adapter = mListAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_,_ ->
            mUserViewModel.deleteAll()
            Toast.makeText(requireContext(), "Successfully removed everething", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") {_,_ ->}
        builder.setTitle("Delete all users?")
        builder.setMessage("Are you sure you want to delete all users?")
        builder.create().show()
    }
}