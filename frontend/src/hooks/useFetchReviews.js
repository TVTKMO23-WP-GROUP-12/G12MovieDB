import { useState, useEffect } from 'react';
{
    //Hook that fetches a list of reviews made by user.
}
const useFetchReviews = (id) => {
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    if (id) {
      fetch(`http://localhost:8080/review/user=${id}`)
        .then(response => response.json())
        .then(data => {
          setReviews(data);
        })
        .catch(error => console.error('Error:', error));
    }
  }, [id]);

  return reviews;
};

export default useFetchReviews;