package com.example.studentmanger

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class StudentListFragment : Fragment() {
    private lateinit var studentAdapter: ArrayAdapter<StudentModel>
    private val students = mutableListOf(
        StudentModel("Nguyễn Văn An", "SV001"),
        StudentModel("Trần Thị Bảo", "SV002"),
        StudentModel("Lê Hoàng Cường", "SV003"),
        StudentModel("Phạm Thị Dung", "SV004"),
        StudentModel("Đỗ Minh Đức", "SV005"),
        StudentModel("Vũ Thị Hoa", "SV006"),
        StudentModel("Hoàng Văn Hải", "SV007"),
        StudentModel("Bùi Thị Hạnh", "SV008"),
        StudentModel("Đinh Văn Hùng", "SV009"),
        StudentModel("Nguyễn Thị Linh", "SV010"),
        StudentModel("Phạm Văn Long", "SV011"),
        StudentModel("Trần Thị Mai", "SV012"),
        StudentModel("Lê Thị Ngọc", "SV013"),
        StudentModel("Vũ Văn Nam", "SV014"),
        StudentModel("Hoàng Thị Phương", "SV015"),
        StudentModel("Đỗ Văn Quân", "SV016"),
        StudentModel("Nguyễn Thị Thu", "SV017"),
        StudentModel("Trần Văn Tài", "SV018"),
        StudentModel("Phạm Thị Tuyết", "SV019"),
        StudentModel("Lê Văn Vũ", "SV020")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)
        val listView: ListView = view.findViewById(R.id.list_view_students)

        studentAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            students
        )
        listView.adapter = studentAdapter

        // Đăng ký context menu
        registerForContextMenu(listView)

        // Xử lý sự kiện click để chỉnh sửa sinh viên
        listView.setOnItemClickListener { _, _, position, _ ->
            val action = StudentListFragmentDirections
                .actionStudentListFragmentToEditStudentFragment(
                    students[position],
                    position
                )
            findNavController().navigate(action)
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Lắng nghe sinh viên mới
        findNavController().currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<StudentModel>("new_student")
            ?.observe(viewLifecycleOwner) { newStudent ->
                students.add(newStudent)
                studentAdapter.notifyDataSetChanged()
            }

        // Lắng nghe sinh viên đã chỉnh sửa
        findNavController().currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Pair<Int, StudentModel>>("updated_student")
            ?.observe(viewLifecycleOwner) { (position, updatedStudent) ->
                students[position] = updatedStudent
                studentAdapter.notifyDataSetChanged()
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_new -> {
                // Điều hướng đến AddStudentFragment
                findNavController().navigate(R.id.action_studentListFragment_to_addStudentFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.student_context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        return when (item.itemId) {
            R.id.menu_edit -> {
                val action = StudentListFragmentDirections
                    .actionStudentListFragmentToEditStudentFragment(
                        students[position],
                        position
                    )
                findNavController().navigate(action)
                true
            }
            R.id.menu_remove -> {
                students.removeAt(position)
                studentAdapter.notifyDataSetChanged()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}