package com.example.rudgn.mobilehw3;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

class MusicData implements Parcelable{

    private String musicTitle;
    private String singer;
    private Uri musicImg;
    private String albumId;
    private String musicId;

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Uri getMusicImg() {
        return musicImg;
    }

    public void setMusicImg(Uri musicImg) {
        this.musicImg = musicImg;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(musicId);
        parcel.writeString(albumId);
        parcel.writeString(musicImg.toString());
        parcel.writeString(musicTitle);
        parcel.writeString(singer);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public MusicData() {

    }

    public MusicData(Parcel in) {
        readFromParcel(in);
    }


    private void readFromParcel(Parcel in){
        musicId = in.readString();
        albumId = in.readString();
        musicImg = Uri.parse(in.readString());
        musicTitle = in.readString();
        singer = in.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MusicData createFromParcel(Parcel in) {
            return new MusicData(in);
        }

        public MusicData[] newArray(int size) {
            return new MusicData[size];
        }
    };
}

class MusicAdapter extends ArrayAdapter<MusicData> {

    private ArrayList<MusicData> item;
    private Context mContext;

    public MusicAdapter( Context context, int resource,  ArrayList<MusicData> item) {
        super(context, resource, item);
        this.mContext = context;
        this.item = item;

    }

    public void setAdapterList(ArrayList<MusicData> list){

        item = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        View v = convertView;
        if(v == null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row_music_list, null);

            holder = new ViewHolder();
            holder.img = v.findViewById(R.id.imgMusic);
            holder.title = v.findViewById(R.id.txt_music_title);
            holder.name = v.findViewById(R.id.txt_singer_name);
            holder.btn =v.findViewById(R.id.isMusic_click);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final MusicData data = item.get(position);
        if(data != null){

            holder.img.setBackgroundResource(R.drawable.musical_note);
            holder.title.setText("곡명");
            holder.name.setText("가수명");
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        return v;
    }

    class ViewHolder {

        ImageView img;
        TextView title;
        TextView name;
        Button btn;
    }
}

public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    private String TAG = "MusicServiceActivity";
    private Context mContext;
    private ListView musicListView;
    private MusicAdapter adapter;

    ArrayList<MusicData> list = new ArrayList<>();

    private serviceReceiver receiver;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "MusicServiceActivity onDestroy");
        unregisterReceiver(receiver);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        mContext = MusicServiceActivity.this;

        init();
        getMusicData();

        IntentFilter filter = new IntentFilter(MusicService.MUSIC_SERVICE_FILTER);
        receiver = new serviceReceiver();
        registerReceiver(receiver, filter);
    }


    private void init(){

        adapter = new MusicAdapter(mContext, android.R.layout.simple_list_item_1, list);
        adapter.setMusicBtnListener(new MusicAdapter.btnClickListener() {
            @Override
            public void MusicBtnClick(int position) {
                ListenMusic(position);
            }
        });
        musicListView = findViewById(R.id.menuList);
        musicListView.setAdapter(adapter);
        musicListView.setOnItemClickListener(this);
    }

    private void getMusicData(){

        //1. 음악파일인지 아닌지, 2. 앨범 아이디, 3. 음원명, 4.가수명, 미디어 파일 아이디(?)
        String[] projection = { MediaStore.Audio.Media.IS_MUSIC, MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media._ID};
        ContentResolver contentResolver = mContext.getContentResolver();
        Cursor cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, // content://로 시작하는 content table uri
                projection, // 어떤 column을 출력할 것인지
                null,  // 어떤 row를 출력할 것인지
                null,
                MediaStore.Audio.Media.TITLE + " ASC"); // 어떻게 정렬할 것인지

        if(cursor != null) {

            while (cursor.moveToNext()) {

                try{
                    // MediaStore.Audio.Media.IS_MUSIC 값이 1이면 mp3 음원 파일입니다.
                    // 그리고 밑에는 mp3 metadata 이미지 파일의 uri값을 얻어낸것입니다.
                    // 이렇게 얻어낸 데이터를 arraylist에 저장합니다.
                    if(cursor.getInt(0) != 0) {
                        Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                        Uri uri = ContentUris.withAppendedId(sArtworkUri, Integer.valueOf(cursor.getString(1)));

                        MusicData data = new MusicData();
                        data.setMusicImg(uri);
                        data.setMusicTitle(cursor.getString(2));
                        data.setSinger(cursor.getString(3));
                        data.setAlbumId(cursor.getString(1));
                        data.setMusicId(cursor.getString(4));

                        list.add(data);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.setAdapterList(list);
                            }
                        });
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        cursor.close();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

    }

    private void ListenMusic(int position){

        Intent intent = new Intent(this, MusicService.class);
        intent.putParcelableArrayListExtra("list", list);
        intent.putExtra("position", position);
        startService(intent);
    }
}