package ba.spark.spark1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.widget.Toast;

import java.io.IOException;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.POWER_SERVICE;
import static ba.spark.spark1.MainActivity.MY_Preferences_Filename;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {
    private List<Post> postList;
    Integer b = -1;
    Context context;
    public PostRecyclerAdapter(Context context,List<Post> pList) {
        this.postList = pList;
        this.context=context;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }
    //*********************************************************************************************************
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.username.setText(postList.get(position).getUsername());
        holder.timeAgo.setText(postList.get(position).getTimeAgo());
        holder.image.setImageResource(postList.get(position).getImage());

        holder.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (postList.get(position).isIs_liked()) {
                    holder.heart.setImageResource(R.drawable.heart_vector_asset);
                    postList.get(position).setIs_liked(false);
                               postList.get(position).save();
                }
                else {
                    holder.heart.setImageResource(R.drawable.redheart_vector_asset);
                    postList.get(position).setIs_liked(true);
                    postList.get(position).save();

          }

            }
        });
         if (postList.get(position).isIs_liked()) {
            holder.heart.setImageResource(R.drawable.redheart_vector_asset);
        }
        else {
            holder.heart.setImageResource(R.drawable.heart_vector_asset);
        }

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View mView;
        public TextView username;
        public TextView timeAgo;
        public ImageView image;
        public ImageView heart, message, forward, bookmark;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            this.username = (TextView) mView.findViewById(R.id.tv_username);
            this.timeAgo = (TextView) mView.findViewById(R.id.tv_time_ago);
            this.image = (ImageView) mView.findViewById(R.id.iv_image);
            this.heart = (ImageView) mView.findViewById(R.id.iv_heart);
            this.message = (ImageView) mView.findViewById(R.id.iv_message);
            this.forward = (ImageView) mView.findViewById(R.id.iv_forward);
            this.bookmark = (ImageView) mView.findViewById(R.id.iv_bookmark);
        }
    }
}
