package com.tm.example.mymvp_retrofit_rxjavatest.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tm.example.mymvp_retrofit_rxjavatest.R;
import com.tm.example.mymvp_retrofit_rxjavatest.entity.BookBean;
import com.tm.example.mymvp_retrofit_rxjavatest.service.presenter.BookPresenter;
import com.tm.example.mymvp_retrofit_rxjavatest.service.view.IBookView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView tvShow;

    private BookPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new BookPresenter(MainActivity.this);
        presenter.onCreate();
        presenter.attachView(bookView);
        initView();
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        tvShow = (TextView) findViewById(R.id.tv_show);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getSearchBooks("金瓶梅", null, 0, 1);
            }
        });
    }

    private IBookView bookView = new IBookView() {
        @Override
        public void onSuccess(BookBean book) {
            List<String> author = book.getBooks().get(0).getAuthor();
            tvShow.setText("作者：" + author.get(0));

        }

        @Override
        public void onFail(String error) {
            Toast.makeText(MainActivity.this, "get search book fail", Toast.LENGTH_SHORT).show();
        }
    };
}
