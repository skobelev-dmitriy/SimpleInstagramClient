package rf.digitworld.simpleinstagramclient.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import rf.digitworld.simpleinstagramclient.R;
import rf.digitworld.simpleinstagramclient.views.adapters.MediaAdapter;
import rf.digitworld.simpleinstagramclient.views.adapters.UsersListAdapter;


/**
 * Created by Дмитрий on 13.07.2015.
 */
public class MediaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView caption, num_likes, time, user_name, num_comments;
    public ImageView imageView, avatar;
    public LinearLayout ll_comments;
    MediaAdapter.OnItemClickListener listener;
    public MediaViewHolder(View itemView) {
        super(itemView);
        avatar= (ImageView) itemView.findViewById(R.id.image_avatar);
        imageView= (ImageView) itemView.findViewById(R.id.imageView);
        caption=(TextView)itemView.findViewById(R.id.text_caption);
        time= (TextView) itemView.findViewById(R.id.text_time);
        user_name=(TextView)itemView.findViewById(R.id.text_user_name);
        num_likes=(TextView)itemView.findViewById(R.id.text_num_likes);
        num_comments=(TextView)itemView.findViewById((R.id.text_num_comments));
        ll_comments=(LinearLayout)itemView.findViewById(R.id.layout_comment);
        ll_comments.setVisibility(View.GONE);

        num_likes.setOnClickListener(this);
        num_comments.setOnClickListener(this);
    }
    public TextView getTime() {
        return time;
    }

    public void setTime(TextView time) {
        this.time = time;
    }
    public TextView getUserName() {
        return user_name;
    }

    public void setUserName(TextView user_name) {
        this.user_name = user_name;
    }

    public TextView getCaption() {
        return caption;
    }

    public void setCaption(TextView caption) {
        this.caption = caption;
    }

    public ImageView getImage() {
        return imageView;
    }

    public void setImage(ImageView imageView) {
        this.imageView = imageView;
    }
    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar= avatar;
    }
    public TextView getLikes(){
        return num_likes;
    }
    public void setLikes(TextView num_likes){
        this.num_likes=num_likes;
    }
    public TextView getComents(){
        return num_comments;
    }
    public void setComments(TextView num_comments){
        this.num_comments=num_comments;
    }
    public LinearLayout getComentsLayout(){
        return ll_comments;
    }
    public void setCommentsLayout(LinearLayout ll_comments){
        this.ll_comments=ll_comments;
    }

    private void toggleLayout(){
        if (ll_comments.getVisibility()==View.GONE){
            ll_comments.setVisibility(View.VISIBLE);
        }else{
            ll_comments.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_num_likes:
          //  listener.onItemClick(num_likes,getLayoutPosition());
                break;
            case R.id.text_num_comments:
                toggleLayout();
                break;
        }
    }
}
