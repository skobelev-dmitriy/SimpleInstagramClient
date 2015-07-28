package rf.digitworld.simpleinstagramclient.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import rf.digitworld.simpleinstagramclient.R;
import rf.digitworld.simpleinstagramclient.models.User;

/**
 * Created by Дмитрий on 14.07.2015.
 */
public class UsersListAdapter extends BaseAdapter {
    List<User> userList;
    Context context;

    public UsersListAdapter (Context context,List<User> list){
        this.userList=list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getCount()!=0) {


            //listView.setVisibility(View.VISIBLE);

            User user=(User)getItem(position);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list_user, null);

            TextView name = (TextView) convertView.findViewById(R.id.text_name);
            ImageView photo = (ImageView) convertView.findViewById(R.id.image_photo);



            name.setText(user.getUsername());
            Picasso.with(context)
                    .load(user.getProfilePicture())
                    .resize(32,32)
                    .centerCrop()
                    .into(photo);

        }else{
            // listView.setVisibility(View.GONE);



        }

        return convertView;
    }
}
