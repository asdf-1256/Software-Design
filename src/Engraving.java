import java.util.*;

public class Engraving {
    public static ArrayList<Accessory> getResult(){
        //UI의 combobox와 textfield를 인자로 전달받아야함.

        ArrayList<Accessory> return_list = new ArrayList<>(5);

        //OPEN API를 통해 경매장에서 검색해서 계산하는 과정 구현 필요

        for(int i = 0; i < return_list.size(); i++){
            return_list.add(new Accessory());
        }
        return return_list;
    }
}

class Accessory{
    String name;
    String accessory_type;
    String first_stat;
    String second_stat;
    int first_stat_value;
    int second_stat_value;
    String first_engraving;
    String second_engraving;
    int first_engraving_value;
    int second_engraving_value;
    String debuff_engraving;
    int debuff_engraving_value;
    int price;

}
