package Placement;

import java.util.Scanner;

public class mainScreen {
    private int sound = 100; // 사운드
    private int resolutionwidth = 1920; // 해상도너비
    private int resolutionheight = 1080; // 해상도높이
    Scanner sc = new Scanner(System.in);
    Placement pm = new Placement();

    boolean validation(int chooseNum) { // 유효성 검사
        if (0 > chooseNum) {
            System.out.println("입력 오류");
            return false;
        }
        return true;
    }

    void main() { // 메인 화면
        System.out.println("메인 화면 입니다.");
        switch (main_choose()) {
        case 1: {
            placement();
            return;
        }
        case 2: {
            checkPokemon();

            return;
        }
        case 3: {
            recode();

            return;
        }
        case 4: {
            option();

            return;
        }
        default:
            return;
        }

    }

    int main_choose() { // 메인화면의 화면 결정
        System.out.print("게임시작(1), 덱 확인(2), 기록확인(3), 설정(4) : ");
        int chooseNum = sc.nextInt();
        if (!validation(chooseNum)) {
            return 0;
        }
        return chooseNum;
    }

    void placement() { // 배치단계
        System.out.println("배치단계 입니다.");
        pm.run();

    }

    void checkPokemon() { // 덱 확인
        System.out.println("덱 확인 입니다.");

    }

    void recode() { // 기록 확인
        System.out.println("기록 확인 입니다.");
    }

    void option() { // 옵션
        System.out.println("옵션 입니다.");
        switch (option_choose()) {
        case 0: {
            break;
        }
        case 1: {
            //option_resolution();
            break;
        }
        case 2: {
            option_sound();
            break;
        }
        default:
            System.out.println("입력 오류");
            break;
        }
        return;
    }

    int option_choose() {
        System.out.print("뒤로가기(0), 해상도 설정(1), 사운드 설정(2) : ");
        int choose_option = sc.nextInt();
        if (!validation(choose_option)) {
            return 0;
        }
        return choose_option;
    }



    void option_sound() { // 사운드 설정
        System.out.println("현제 사운드는 " + sound + "% 입니다.");
        while (true) {
            System.out.print("설정할 사운드를 입력해주세요 : ");
            int set_sound = sc.nextInt();
            if (0 <= set_sound && set_sound <= 100) {
                sound = set_sound;
                System.out.println("설정 되었습니다!");
                break;
            } else {
                System.out.println("수는 0~100 사이 입니다.");
            }
        }
        return;
    }

    void run() { // 구동
        System.out.println("게임을 시작합니다.");

        while (true) {
            main();

        }
    }

    public static void main(String[] args) {
        mainScreen m = new mainScreen();
        m.run();
    }

}
