package com.example.richard.contact;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends Activity {
    private static final String TAG = "ContactActivity";
    private ListView mListView;
    private List mContactList;
    private String[] mNameList;
    private SideBar mSideBar;
    private ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initView();
        initData();
        mAdapter = new ContactAdapter(mContactList, this);
        mListView.setAdapter(mAdapter);
        setSideBarListener();
    }

    private void initData() {
        mNameList = new String[]{"阿威", "阿妹", "变tai", "变帅", "曹操", "曹丕", "曹植", "帝王",
                "弟弟", "二哥", "二姐", "富豪", "夫人", "哥哥", "歌王", "皇上", "黄色", "I哥", "I姐",
                "姐姐", "借钱的", "K歌王", "KK", "靓仔", "亮剑兄", "妈妈", "陌陌小妹", "农业哥", "农家乐大姐",
                "O姐", "O哥", "漂亮妹妹", "漂白粉哥", "强仔", "强抢民女", "日本", "日系", "爽妹", "双龙哥",
                "体态哥", "替身弟弟", "UFO", "U贴", "V哥", "VV妹", "未来", "威哥", "小妹", "小哥", "一字眉",
                "乙方", "装逼哥", "壮壮", "&牛", "￥人"};
        mContactList = new ArrayList();
        for (int i = 0; i < mNameList.length; i++) {
            Contact contact = new Contact();
            contact.setName(mNameList[i]);
            mContactList.add(contact);
        }
        generatePinYin(mContactList);
        //todo  sort
    }


    private void generatePinYin(List<Contact> contactList) {
        for (Contact contact : contactList) {

            if (contact.getPinyin() == null) {

                char[] chars = contact.getName().toCharArray();
                String[] pinyin = new String[chars.length];
                boolean flag = true;
                for (int i = 0; i < chars.length; i++) {
                    pinyin[i] = PinYinUtil.getPingYin(String.valueOf(chars[i]));
                    if (TextUtils.isEmpty(pinyin[i])) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    Log.i(TAG, contact.getName() + " generate pinyin failed.");
                    continue;
                }
                String firstChar = pinyin[0].substring(0, 1);
                if (firstChar.matches("[A-Z]")) {
                    contact.setPinyin(pinyin);
                } else {
                    contact.setPinyin(new String[]{"#"});
                }
            }
        }
    }


    private void initView() {
        mListView = (ListView) findViewById(R.id.list_view);
        mSideBar = (SideBar) findViewById(R.id.side_bar);
    }

    private void setSideBarListener() {
        mSideBar.setLetterChangedListener(new SideBar.LetterChangedListener() {
            @Override
            public void onLetterChanged(String s) {
                int position = mAdapter.getPositionForSection(s.charAt(0));
                if (position >= 0) {
                    mListView.setSelection(position);
                }
            }
        });
    }
}
