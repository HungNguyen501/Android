package com.example.gmailapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ItemModel> items;
    ListView listView;
    ItemAdapter adapter;
    AutoCompleteTextView ac_search;
    boolean show_list_favorite = false;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<ItemModel>();
        items.add(new ItemModel("Hung Nguyen", "Thu chao hoi", "Ban co khoe khong?", "8:00PM"));
        items.add(new ItemModel("Thu Thuy Nguyen", "Phong van", "Hom nay co duoc khong ban oi? neu duoc thi ta co the phong van vao hom nay.", "10:00PM"));
        items.add(new ItemModel("Chung",
                "Thu gioi thieu", "Minh xin tu gioi thieu, minh la Huyen! Minh la tu van vien cua cong ty bat dong san.", "8:00AM"));
        items.add(new ItemModel("Hoang Tuyen", "Chao hoi", "Hom nay ban the nao?", "9:00AM"));
        items.add(new ItemModel("Vu Vinh", "Xin gap mat", "Chao ban, bon minh gap vao mai nhe.", "10:00PM"));
        items.add(new ItemModel("Thu Thuy", "Hello ban eyy", "ban co khoe khong? Di choi khong ban oi", "5:00PM"));
        items.add(new ItemModel("Viet Van Toan", "Thu chao hoi", "ban co khoe khong?", "8:00PM"));
        items.add(new ItemModel("Hung", "Thu chao hoi", "Dao nay the nao roi ban, toi di zay dam khong?", "11:00AM"));
        items.add(new ItemModel("Hung Vu", "Thu moi phong van", "Chao ban, ngay mai ban co ranh khong. Chung toi co the he ban phong van duoc khong.", "6:00AM"));
        items.add(new ItemModel("Toan Van", "Thu chao hoi", "Yeu mot nguoi co phai de dang khong?", "8:00PM"));
        items.add(new ItemModel("Le Ba", "Don xac nhan mua hang", "Ban duoc xac nhan mua san pham nay.", "6:00PM"));
        items.add(new ItemModel("Viet Quyet Nguyen", "Thu chao hoi, ban co yeu minh khong. Minh yeu ban rat nhieu, lam, lamlam.", "ban co khoe khong?", "8:00PM"));
        items.add(new ItemModel("An An", "Game k ban oi", "Toi afk ban oi, lam tran di ban nhe!?", "11:00PM"));

        adapter = new ItemAdapter(items);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        List<ItemModel> duplicateItems = new ArrayList<>(items);
        ac_search = findViewById(R.id.ac_search);
        AutoCompleteItemAdapter adapter2 = new AutoCompleteItemAdapter(this, duplicateItems);
        ac_search.setAdapter(adapter2);

        findViewById(R.id.btn_favorite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_list_favorite = !show_list_favorite;

                if (show_list_favorite) {
                    listView.setAdapter(getAdapter_List_Favorite());
                } else {
                    listView.setAdapter(adapter);
                }
            }
        });

    }

    public ItemAdapter getAdapter_List_Favorite() {
        List<ItemModel> list_favorite;
        ItemAdapter adapter;

        list_favorite = new ArrayList<ItemModel>();
        for (int i = 0; i < items.size(); i++)
            if (items.get(i).isFavorite())
                list_favorite.add(items.get(i));

        adapter = new ItemAdapter(list_favorite);
        return adapter;
    }
}
