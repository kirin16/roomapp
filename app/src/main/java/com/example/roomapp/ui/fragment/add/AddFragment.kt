package com.example.roomapp.ui.fragment.add

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.data.model.OneMoreModel
import com.example.roomapp.data.model.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mName: EditText
    private lateinit var mSurname: EditText
    private lateinit var mAge: EditText
    private lateinit var mBtnAdd: Button

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        initView(view)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        mBtnAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun initView (view: View) {
        mName = view.findViewById<View>(R.id.update_name) as EditText
        mSurname = view.findViewById<View>(R.id.update_surname) as EditText
        mAge = view.findViewById<View>(R.id.et_age) as EditText
        mBtnAdd = view.findViewById<View>(R.id.button_add) as Button
    }

    private fun insertDataToDatabase() {
        val name = mName.text.toString()
        val surname = mSurname.text.toString()
        val age = mAge.text

        if (inputCheck(name, surname, age)) {
            val onaMoreModel = OneMoreModel(Integer.parseInt(age.toString()), age.toString())
            val user = User(0, name, surname, Integer.parseInt(age.toString()), listOf(onaMoreModel))
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "User added successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String, surname: String, age: Editable) : Boolean{
        return !(name.isEmpty() && surname.isEmpty() && age.isEmpty())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}