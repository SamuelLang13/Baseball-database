import axios from "axios";

const PLAYERS_REST_API_URL = 'https://localhost:8080/baseballDatabase/players'

class PlayerService{
    getUsers(){
        return axios.get(PLAYERS_REST_API_URL);
    }
}

export default new PlayerService();