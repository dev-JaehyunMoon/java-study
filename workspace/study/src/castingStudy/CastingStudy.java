package castingStudy;

public class CastingStudy {
	// 넷플릭스
	// 애니메이션, 영화, 드라마 클래스 선언
	// 사용자가 선택한 영상이
	// 애니메이션이라면 "자막지원"기능 사용
	// 영화라면 "4D"기능 사용
	// 드라마라면 "굿즈"기능 사용
	public void checkNetflix(Netflix netflix) {
		if(netflix instanceof Animation) {
			Animation animation = (Animation) netflix;
			animation.getSubtitle();
			
		}else if(netflix instanceof Movie) {
			Movie movie = (Movie) netflix;
			movie.movieFour();
		}else if(netflix instanceof Drama){
			Drama drama = (Drama) netflix;
			drama.sellGoods();
		}
		
	}
	public static void main(String[] args) {
		Netflix[] netflix = {new Animation(), new Movie(), new Drama()};
		CastingStudy castingStudy = new CastingStudy();
		
		for (int i = 0; i < netflix.length; i++) {
			castingStudy.checkNetflix(netflix[i]);
		}
	}
}
