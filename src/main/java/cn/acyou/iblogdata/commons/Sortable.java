package cn.acyou.iblogdata.commons;

import java.util.List;

public interface Sortable {

    List<Sort> getSorts();

    void addSort(Sort sort);

}
