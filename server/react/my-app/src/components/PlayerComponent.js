import userEvent from "@testing-library/user-event";
import React from "react";
import PlayerService from "../services/PlayerService";

class PlayerComponent extends React.Component{

    constructor(props){
        super(props)
        this.state={
            players:[]
        }
    }

    componentDidMount(){
        PlayerService.getUsers().then((response)=>{
            this.setState({players:response.data})
        });
    }

    render(){
        return(
            <div>
                <h1 className="text-center">Players List</h1>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <td>Player Id</td>
                            <td>Player first name</td>
                            <td>Player second name</td>
                            <td>Player position</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.players.map(
                                player=>
                                <tr key = {player.id}>
                                    <td>{player.id}</td>
                                    <td>{player.firstName}</td>
                                    <td>{player.SecondName}</td>
                                    <td>{player.position}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        )
    }
}

export default new PlayerComponent();
