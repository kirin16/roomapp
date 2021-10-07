package com.example.roomapp.ui.fragment.update

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomapp.R
import com.example.roomapp.data.model.OneMoreModel
import com.example.roomapp.data.model.User
import com.example.roomapp.databinding.FragmentUpdateBinding
import com.example.roomapp.ui.fragment.add.UserViewModel

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        fillData()


        binding?.buttonUpdate?.setOnClickListener {
            val name = binding!!.updateName.text
            val surname = binding!!.updateSurname.text
            val age = Integer.parseInt(binding!!.updateAge.text.toString())
            val onaMoreModel = OneMoreModel(age, age.toString())
            val updatedUser = User(args.currentUser.id, name.toString(), surname.toString(), age, listOf(onaMoreModel))
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.listFragment)
        }
        val view = binding?.root

        setHasOptionsMenu(true)

        return view
    }


    private fun fillData() {
        binding!!.updateName.setText(args.currentUser.firstName)
        binding!!.updateSurname.setText(args.currentUser.lastName)
        binding!!.updateAge.setText(args.currentUser.age.toString())
        Log.e("hui", args.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Successfully removed: ${args.currentUser.firstName}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.listFragment)
        }
        builder.setNegativeButton("No") {_,_ ->}
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }

}