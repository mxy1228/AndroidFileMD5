package md5.meng.com.androidfilemd5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import md5.meng.com.androidfilemd5.security.SecurityHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testMD5();
    }

    void testMD5(){
        String filePath = "";
        SecurityHelper.getInstance()
                .fileMD5Async(filePath)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        // TODO
                        unsubscribe();
                    }
                });
    }
}
