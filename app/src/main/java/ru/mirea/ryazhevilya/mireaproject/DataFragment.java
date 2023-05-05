package ru.mirea.ryazhevilya.mireaproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import ru.mirea.ryazhevilya.mireaproject.databinding.FragmentDataBinding;


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlMap.get(selectedInternship);
                if(!url.equals("")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getContext(), "Вы не выбрали направление!", Toast.LENGTH_SHORT);
                }
            }
        });
        binding.textViewInternshipDescr.setText("Выберите направление и здесь появится описание задач!");
        binding.materialButtonDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewInternshipDescr.setText("Примеры задач, с которыми сталкиваются стажёры: \n- Разработать меню в приложении Yandex Go" +
                " \n- Обновить дизайн Яндекс.Переводчика \n- Улучшить интерфейс голосового помощника Алисы");
        binding.textViewInternshipName.setText("<1.Мобильная разработка/>");
        selectedInternship = DEV_INTERN;
            }
        });
        binding.materialButtonAnalytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewInternshipDescr.setText("Примеры задач, с которыми сталкиваются стажёры: \n- Измерить уровень счастья пользователей Алисы" +
                        " \n- Проанализировать поведение пользователей Яндекс.Погоды \n- Улучшить алгоритмы геолокации");
                binding.textViewInternshipName.setText("<2.Анализ данных/>");
                selectedInternship = ANALYTICS_INTERN;
            }
        });
        binding.materialButtonInfosec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewInternshipDescr.setText("Примеры задач, с которыми сталкиваются стажёры: \n- Участие в процессах SDL" +
                        " \n- Консультирование разработчиков по вопросам безопасной разработки \n- Создавать инструменты разработки");
                binding.textViewInternshipName.setText("<3.Информационная безопасность/>");
                selectedInternship = INFOSEC_INTERN;
            }
        });
        binding.materialButtonTesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewInternshipDescr.setText("Примеры задач, с которыми сталкиваются стажёры: \n- Анализ тестового покрытия" +
                        " \n- Создание документации \n- Тестирование нового функционала");
                binding.textViewInternshipName.setText("<4.Информационная безопасность/>");
                selectedInternship = TESTING_INTERN;
            }
        });
        return root;

    }
}