package ru.mirea.ryazhevilya.mireaproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import ru.mirea.ryazhevilya.mireaproject.databinding.FragmentDataBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataFragment extends Fragment {
    private FragmentDataBinding binding;

    private static final int DEV_INTERN = 1;
    private static final int ANALYTICS_INTERN = 2;
    private static final int INFOSEC_INTERN = 3;
    private static final int TESTING_INTERN = 4;
    private static final Map<Integer, String> urlMap = new HashMap<>();
    static {
        urlMap.put(0, "");
        urlMap.put(DEV_INTERN, "https://yandex.ru/yaintern/int_04");
        urlMap.put(ANALYTICS_INTERN, "https://yandex.ru/yaintern/int_02");
        urlMap.put(INFOSEC_INTERN, "https://yandex.ru/yaintern/security");
        urlMap.put(TESTING_INTERN, "https://yandex.ru/yaintern/qa");

    }
    private int selectedInternship = 0;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataFragment newInstance(String param1, String param2) {
        DataFragment fragment = new DataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        binding = FragmentDataBinding.inflate(getLayoutInflater());
        getActivity().setContentView(binding.getRoot());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false);

    }

    public void onClickShowDev(View view) {

        binding.textViewInternshipDescr.setText("Примеры задач, с которыми сталкиваются стажёры: \n- Разработать меню в приложении Yandex Go" +
                " \n- Обновить дизайн Яндекс.Переводчика \n- Улучшить интерфейс голосового помощника Алисы");
        binding.textViewInternshipName.setText("<1.Мобильная разработка/>");
        selectedInternship = DEV_INTERN;
    }

    public void onClickShowAnalytics(View view){
        binding.textViewInternshipDescr.setText("Примеры задач, с которыми сталкиваются стажёры: \n- Измерить уровень счастья пользователей Алисы" +
                " \n- Проанализировать поведение пользователей Яндекс.Погоды \n- Улучшить алгоритмы геолокации");
        binding.textViewInternshipName.setText("<2.Анализ данных/>");
        selectedInternship = ANALYTICS_INTERN;
    }

    public void onClickShowInfosec(View view){

        binding.textViewInternshipDescr.setText("Примеры задач, с которыми сталкиваются стажёры: \n- Участие в процессах SDL" +
                " \n- Консультирование разработчиков по вопросам безопасной разработки \n- Создавать инструменты разработки");
        binding.textViewInternshipName.setText("<3.Информационная безопасность/>");
        selectedInternship = INFOSEC_INTERN;
    }

    public void onClickShowTesting(View view){
        binding.textViewInternshipDescr.setText("Примеры задач, с которыми сталкиваются стажёры: \n- Анализ тестового покрытия" +
                " \n- Создание документации \n- Тестирование нового функционала");
        binding.textViewInternshipName.setText("<4.Информационная безопасность/>");
        selectedInternship = TESTING_INTERN;
    }



    public void onClickGoToInternship(View view) {
        String url = urlMap.get(selectedInternship);
        if(!url.equals("")){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
        else{
            Toast.makeText(this.getContext(), "Вы не выбрали направление!", Toast.LENGTH_SHORT).show();
        }
    }


}