const api = {
  async indexAll(
    games = "http://localhost:8080/games",
    consoles = "http://localhost:8080/consoles",
    //tShirts = "http://ec2-18-191-254-218.us-east-2.compute.amazonaws.com:8080/t-shirts"
    tShirts = "http://localhost:8080/t-shirts"
  ) {
    let gamesRes;
    let consolesRes;
    let tShirtsRes;

    try {
      gamesRes = await fetch(games);
      consolesRes = await fetch(consoles);
      tShirtsRes = await fetch(tShirts);
    } catch (err) {
      console.log("Sorry, an error occurred! " + err);
    }

    if (gamesRes && consolesRes && tShirtsRes) {
      const gameArr = await gamesRes.json();
      const consoleArr = await consolesRes.json();
      const tShirtArr = await tShirtsRes.json();

      return gameArr.concat(consoleArr, tShirtArr);
    } else return "Could not find users";
  },

  async updateGame(data) {
    const response = await fetch(`http://localhost:8080/games`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    });
    let responseText = await response.text();
    console.log(responseText);
    return responseText;
  },

  async deleteGame(id) {
    const response = await fetch(`http://localhost:8080/games/${id}`)
  }
};

export default api;
