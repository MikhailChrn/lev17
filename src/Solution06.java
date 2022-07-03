public class Solution06 {
    //lev 17: И снова Singleton паттерн - синхронизация в статическом блоке.
    //Внутри класса OurPresident в статическом блоке создай синхронизированный блок.
    //Внутри синхронизированного блока инициализируй president.

    public static void main(String[] args) {
        OurPresident expectedPresident = OurPresident.getOurPresident();
        OurPresident ourPresident = OurPresident.getOurPresident();
        System.out.println(expectedPresident == ourPresident);
    }
}
