package com.tenz.tenzmusic.widget.dialog;

import java.io.Serializable;

public interface ViewConvertListener extends Serializable {

    long serialVersionUID = System.currentTimeMillis();

    void convertView(ViewHolder holder, BaseDialog dialog);

}
