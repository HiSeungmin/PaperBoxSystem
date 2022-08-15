package com.example.paperbox.ui.main;

import android.app.Application;
import android.content.Intent;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.paperbox.R;
import com.example.paperbox.SubMain;

public class PageViewModel extends ViewModel {

    Fragment selected = null;
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {

        @Override
        public String apply(Integer input) {

            if(input==1)
            {
                return "여기는 무게 측정 tab 입니다. 무게를 측정하세요." + input;

            }else if(input==2) {
                return "여기는 환급 tab 입니다. 환급을 계산해보세요." + input;

            }else {
                return "여기는 내 정보 tab입니다. 내 정보를 확인해보세요. " + input;

            }

        }


    });



    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}