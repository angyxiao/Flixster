package peapod.angela.flixster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import peapod.angela.flixster.models.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    // the movie to display
    Movie movie;

    // butterknife, track view objects
    @Nullable @BindView(R.id.ivPosterImage) ImageView ivPosterImage;
    @Nullable @BindView(R.id.ivBackdropImage) ImageView ivBackdropImage;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvOverview) TextView tvOverview;
    @BindView(R.id.rbVoteAverage) RatingBar rbVoteAverage;
    @BindView(R.id.popularity) TextView popularity;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        // unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        // set the title and overview, and popularity
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        popularity.setText(String.valueOf(movie.getPopularity()));

        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage = voteAverage > 0 ? voteAverage / 2.0f : voteAverage);
    }
}
