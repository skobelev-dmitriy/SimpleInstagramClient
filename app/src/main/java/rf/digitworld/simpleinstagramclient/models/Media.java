package rf.digitworld.simpleinstagramclient.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 13.07.2015.
 */
public class Media {


        @Expose
        private Pagination pagination;
        @Expose
        private Meta meta;
        @Expose
        private List<Post> data = new ArrayList<Post>();

        /**
         *
         * @return
         *     The pagination
         */
        public Pagination getPagination() {
            return pagination;
        }

        /**
         *
         * @param pagination
         *     The pagination
         */
        public void setPagination(Pagination pagination) {
            this.pagination = pagination;
        }

        /**
         *
         * @return
         *     The meta
         */
        public Meta getMeta() {
            return meta;
        }

        /**
         *
         * @param meta
         *     The meta
         */
        public void setMeta(Meta meta) {
            this.meta = meta;
        }

        /**
         *
         * @return
         *     The data
         */
        public List<Post> getData() {
            return data;
        }

        /**
         *
         * @param data
         *     The data
         */
        public void setData(List<Post> data) {
            this.data = data;
        }

    }


