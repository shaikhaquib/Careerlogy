package com.DIS.careerlogy.Fragment;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.DIS.careerlogy.MainActivity;
import com.DIS.careerlogy.Network.RetrofitClient;
import com.bumptech.glide.Glide;
import com.DIS.careerlogy.Extra.MyItemDecoration;
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.Extra.UpdateTitle;
import com.DIS.careerlogy.Models.TestimonialResponse;
import com.DIS.careerlogy.Models.YouTubeVideoListItem;
import com.DIS.careerlogy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Testimonial extends Fragment {


    MainActivity activity;
    UpdateTitle updateTitle;
    RecyclerView rvVideo;
    List<YouTubeVideoListItem> list = new ArrayList<>();

    public Testimonial() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity= (MainActivity) getActivity();
        updateTitle = activity.updateTitle;
       // updateTitle.updateData("Testimonial Video");
        return inflater.inflate(R.layout.fragment_testimonial, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       /* String url = "https://img.youtube.com/vi/"+youtubeIds.get(id)+"/0.jpg";
        Glide.with(getApplicationContext()).load(url).into(thumb);*/

        rvVideo = view.findViewById(R.id.rvVideo);
        rvVideo.setHasFixedSize(true);
        rvVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvVideo.addItemDecoration(new MyItemDecoration());
        rvVideo.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new VideoHolder(LayoutInflater.from(getActivity()).inflate(R.layout.testimonial_item,parent,false));
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                VideoHolder videoHolder = (VideoHolder) holder;
                YouTubeVideoListItem listItem = list.get(position);
                final String id = getYTId(listItem.getYTTVideoLink());

                String url = "https://img.youtube.com/vi/"+id+"/0.jpg";
                Glide.with(getActivity()).load(url).into(videoHolder.vdThumb);
                videoHolder.vdTitle.setText(listItem.getYTTTitle());
                videoHolder.vdDesc.setText(listItem.getYTTDescription());


                videoHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
                        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://www.youtube.com/watch?v=" + id));
                        try {
                            startActivity(appIntent);
                        } catch (ActivityNotFoundException ex) {
                            startActivity(webIntent);
                        }
                    }
                });


            }

            @Override
            public int getItemCount() {
                return list.size();
            }

            class VideoHolder extends RecyclerView.ViewHolder {
                ImageView vdThumb;
                TextView vdTitle,vdDesc;

                public VideoHolder(@NonNull View itemView) {
                    super(itemView);
                    vdThumb = itemView.findViewById(R.id.videoThumb);
                    vdTitle = itemView.findViewById(R.id.videoTitle);
                    vdDesc = itemView.findViewById(R.id.videoDesc);
                }
            }
        });
        loadVideo("0");

    }

    public static String getYTId(String ytUrl) {
        String vId = null;
        Pattern pattern = Pattern.compile(
                "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ytUrl);
        if (matcher.matches()){
            vId = matcher.group(1);
        }
        return vId;
    }

    public void loadVideo(String offset) {
        final Progress progress =new Progress(getActivity());
        progress.show();
        Call<TestimonialResponse> call= RetrofitClient.getInstance().getApi().YouTubeVideoList(offset);
        call.enqueue(new Callback<TestimonialResponse>() {
            @Override
            public void onResponse(Call<TestimonialResponse> call, Response<TestimonialResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    list .addAll( response.body().getYouTubeVideoList());
                    rvVideo.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TestimonialResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }

}
