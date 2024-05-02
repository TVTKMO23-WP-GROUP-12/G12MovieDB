import React from "react";

function ShareUser({ userId }) {
  const handleShare = async () => {
    const shareDetails = {
      url: window.location.href,
      title: "Jaettava sisältö", // You can customize the title as needed
      text: "", // You can customize the shared content as needed
    };

    if (navigator.share) {
      try {
        await navigator.share(shareDetails);
        console.log("Sharing success!");
      } catch (error) {
        console.log(`Sharing error: ${error}`);
      }
    } else {
      console.log(
        "Web share is currently not supported on this browser. Please provide a fallback."
      );
    }
  };

  return (
    <button className="share-button" onClick={handleShare}>
      Jaa
    </button>
  );
}

export default ShareUser;