package rf.digitworld.simpleinstagramclient.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Дмитрий on 13.07.2015.
 */
public class Meta {


        @Expose
        private int code;

        /**
         *
         * @return
         *     The code
         */
        public int getCode() {
            return code;
        }

        /**
         *
         * @param code
         *     The code
         */
        public void setCode(int code) {
            this.code = code;
        }

    }


