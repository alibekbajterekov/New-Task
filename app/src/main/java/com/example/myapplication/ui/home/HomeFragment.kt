package com.example.myapplication.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*

import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort_menu) {

            val items = arrayOf("Дате", "A-z", "z-A")

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Сортировать по:")
            builder.setItems(items) { dialog, which ->
                when (which) {
                    0 -> {
                       adapter.addTasks(App.database.taskDao()?.getAllTaskByDate() as List<TaskModel>)
                    }
                    1 -> {
                        adapter.addTasks(App.database.taskDao()?.getAllTaskByAlphabetAz() as List<TaskModel>)
                    }
                    2 -> {
                        adapter.addTasks(App.database.taskDao()?.getAllTaskByAlphabetZa() as List<TaskModel>)
                    }
                }
            }
            builder.show()
        }
        return super.onContextItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        Log.e("ololo", "onViewCreated")
    }

    private fun initListeners() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.newTaskFragment)
        }
    }

    private fun initViews() {
        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = adapter

        setData()
        /*       setFragmentResultListener( "new_task") {key, bundle ->
            val title = bundle.getString("title")
            val desc = bundle.getString("desc")
            Log.e("ololo", "initViews: $title и $desc")
adapter.addTask(TaskModel(title, desc))
        }*/

        val listOfTasks = App.database.taskDao()?.getAllTasks()
        adapter.addTasks(listOfTasks as List<TaskModel>)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::onLongClickListiner)

    }

    fun onLongClickListiner(pos: Int) {

    }
    fun setData(){
        val listOfTasks = App.database.taskDao()?.getAllTasks()
        adapter.addTasks(listOfTasks as List<TaskModel>)
    }


    companion object {
        const val KEY_FOR_TASK = "task"
    }
}
