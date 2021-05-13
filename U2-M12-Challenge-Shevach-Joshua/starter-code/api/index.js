const api = {
  async indexAll(
    //games = "http://localhost:8080/games", //<~~ for testing
    games = "http://ec2-18-191-254-218.us-east-2.compute.amazonaws.com:8080/games", //<~~ for prod
    //consoles = "http://localhost:8080/consoles", //<~~ for testing
    consoles = "http://ec2-18-191-254-218.us-east-2.compute.amazonaws.com:8080/consoles", //<~~ for prod
    //tShirts = "http://localhost:8080/t-shirts" //<~~ for testing
    tShirts = "http://ec2-18-191-254-218.us-east-2.compute.amazonaws.com:8080/t-shirts" //<~~ for prod
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
};

export const gameApi = {
  async updateGame(data) {
    const response = await fetch(`http://ec2-18-191-254-218.us-east-2.compute.amazonaws.com:8080/games`, {
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
    const response = await fetch(`http://ec2-18-191-254-218.us-east-2.compute.amazonaws.com:8080/games/${id}`, {
      method: "DELETE",
    });
  },

  async createGame(data) {
    const response = await fetch(`http://ec2-18-191-254-218.us-east-2.compute.amazonaws.com:8080/games`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    });
    let responseText = await response.text();
    console.log(responseText);
    return responseText;
  }
}

export default api;
