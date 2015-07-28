package rf.digitworld.simpleinstagramclient.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Дмитрий on 13.07.2015.
 */
public class Comment {


        @SerializedName("created_time")
        @Expose
        private String createdTime;
        @Expose
        private String text;
        @Expose
        private User from;
        @Expose
        private String id;

        /**
         *
         * @return
         *     The createdTime
         */
        public String getCreatedTime() {
            return createdTime;
        }

        /**
         *
         * @param createdTime
         *     The created_time
         */
        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        /**
         *
         * @return
         *     The text
         */
        public String getText() {
            return text;
        }

        /**
         *
         * @param text
         *     The text
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         *
         * @return
         *     The from
         */
        public User getFrom() {
            return from;
        }

        /**
         *
         * @param from
         *     The from
         */
        public void setFrom(User from) {
            this.from = from;
        }

        /**
         *
         * @return
         *     The id
         */
        public String getId() {
            return id;
        }

        /**
         *
         * @param id
         *     The id
         */
        public void setId(String id) {
            this.id = id;
        }

    }


