package com.ord.model;

import com.fooditem.model.FooditemVO;

public class OrdFooditemVO extends FooditemVO {
        private int quantity;
        
        public OrdFooditemVO() {}
        public OrdFooditemVO(String fo_no, String fo_resno, String fo_name, Integer fo_price, String fo_intro, String fo_status,int quantity){
            super();
            this.quantity=quantity;
        }

        public  OrdFooditemVO(FooditemVO fooditemVO,int quantity){
            this(fooditemVO.getFo_no(),fooditemVO.getFo_resno(),fooditemVO.getFo_name(),fooditemVO.getFo_price(),fooditemVO.getFo_intro(),fooditemVO.getFo_status(),quantity);
            this.quantity=quantity;
        }

        public int getQuantity(){return  quantity;}
        public void  setQuantity(int quantity){this.quantity=quantity;}

}
