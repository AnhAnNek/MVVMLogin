package com.vanannek.mvvmlogin;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    MutableLiveData<Integer> mProgressMutableData = new MutableLiveData<>();
    MutableLiveData<String> mDrinksMutableData = new MutableLiveData<>();
    MutableLiveData<String> mLoginResultMutableData = new MutableLiveData<>();

    MainRepository mMainRepository;

    public MainViewModel() {
        mProgressMutableData.postValue(View.INVISIBLE);
        mDrinksMutableData.postValue("");
        mLoginResultMutableData.postValue("Not logged in");
        mMainRepository = new MainRepository();
    }

    public void login(String email, String password) {
        mProgressMutableData.postValue(View.VISIBLE);
        mLoginResultMutableData.postValue("Checking");
        mMainRepository.loginRemote(new LoginBody(email, password), new MainRepository.ILoginResponse() {
            @Override
            public void onResponse(LoginResponse loginResponse) {
                mProgressMutableData.postValue(View.INVISIBLE);
                mLoginResultMutableData.postValue("Login Success");
            }

            @Override
            public void onFailure(Throwable t) {
                mProgressMutableData.postValue(View.INVISIBLE);
                mLoginResultMutableData.postValue("Login failure: " + t.getLocalizedMessage());
            }
        });
    }

    public void suggestNewDrink() {
        mProgressMutableData.postValue(View.VISIBLE);
        mMainRepository.suggestNewDrink(new MainRepository.IDrinkCallback() {
            @Override
            public void onDrinkSuggested(String drinkName) {
                mProgressMutableData.postValue(View.INVISIBLE);
                mDrinksMutableData.postValue(drinkName);
            }

            @Override
            public void onErrorOccurred() {
                mProgressMutableData.postValue(View.INVISIBLE);
                // show toast with error
            }
        });
    }

    public MutableLiveData<String> getDrink() {
        return mDrinksMutableData;
    }

    public MutableLiveData<String> getLoginResult() {
        return mLoginResultMutableData;
    }

    public MutableLiveData<Integer> getProgress() {
        return mProgressMutableData;
    }
}
