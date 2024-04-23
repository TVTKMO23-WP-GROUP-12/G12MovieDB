import api from '../api/Api.js';

export default async function handleSearch(searchTerm, isFilterActive, movieGenre, tvGenre, rating, releaseYear, setMovies, setTvShows) {
  try {
    let page = 1;
    let allResults = [];

    while (true) {
      const response = await api.get('/public/search', {
        params: {
          query: searchTerm,
          page: page
        }
      });

      const filteredResults = response.data.results.filter(result => result.media_type !== "person");

      allResults = [...allResults, ...filteredResults];

      // Break the loop if there are no more pages to fetch or reached the maximum number of pages
      if (page >= response.data.total_pages || page >= 500) {
        break;
      }

      // Increment page number for the next request
      page++;
    }

    const filteredMovies = allResults.filter(result => {
      if (result.title) {
        if (!isFilterActive) return true;
        if (movieGenre && !result.genre_ids.some(id => id.toString() === movieGenre)) return false;
        if (rating.min && result.vote_average < rating.min) return false;
        if (rating.max && result.vote_average > rating.max) return false;
        const releaseYearValue = new Date(result.release_date).getFullYear();
        if (releaseYear.min && releaseYearValue < releaseYear.min) return false;
        if (releaseYear.max && releaseYearValue > releaseYear.max) return false;
        return true;
      }
      return false;
    });

    const filteredTvShows = allResults.filter(result => {
      if (result.name) {
        if (!isFilterActive) return true;
        if (tvGenre && !result.genre_ids.some(id => id.toString() === tvGenre)) return false;
        if (rating.min && result.vote_average < rating.min) return false;
        if (rating.max && result.vote_average > rating.max) return false;
        const releaseYearValue = new Date(result.first_air_date).getFullYear();
        if (releaseYear.min && releaseYearValue < releaseYear.min) return false;
        if (releaseYear.max && releaseYearValue > releaseYear.max) return false;
        return true;
      }
      return false;
    });

    setMovies(filteredMovies);
    setTvShows(filteredTvShows);
    
    console.log("Elokuvat:", filteredMovies);
    console.log("TV-sarjat:", filteredTvShows);

  } catch (error) {
    console.error('Error fetching search results:', error);
  }
}
