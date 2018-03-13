import React, { Component } from 'react';
import { Jumbotron, Table } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { API_URL } from '../../resources/constants.jsx';

class ProfileAboutPage extends Component{
	constructor(){
		super();
		this.state={
			user: null,
			requests: undefined,
			userLoaded: false,
			requetsLoaded: false,
		}
	}

	componentWillMount(){
		this.getUser();
		this.getRequests();
	}
	getUser(){
		let apiURL = API_URL + 'api/user/get';
		fetch(apiURL, {
			method: 'GET',
			credentials: 'include',
		})
		.then((response) => {
			if(response.ok){
				response.json().then(json => {
					console.log(json);
					this.setState({
						user: json,
						userLoaded:true,
					});
				})
			}else{
				console.log("User not logged in")
			}
		})
		.catch((error) => {
			console.error(error);
		});
	}

	//get all the users requests to respond to
	getRequests(){
		let apiURL = API_URL + 'api/user/requests/sent';
		fetch(apiURL, {
			method: 'GET',
			credentials: 'include',
		})
		.then((response) => {
			if(response.ok){
				response.json().then(json => {
					console.log(json);
					this.setState({
						requests: json,
						requetsLoaded: true,
					});
				})
			}else{
				console.log("User not logged in")
			}
		})
		.catch((error) => {
			console.error(error);
		});
	}

	render(){

		if(this.state.user === null){
			return(
				<Jumbotron><h3>Please Log in.</h3></Jumbotron>
			);
		}else{
			return(
				<center>
					<div>
						<Jumbotron>
							<h1>{this.state.user.name}'s Profile</h1>
							<div className='stats'>
								<Table condensed hover>
									<thead>
									<tr>
										<center><strong><td>User Stats</td></strong></center>
									</tr>
									</thead>
									<tbody>
									<tr>
										<td>Wins:</td>
										<td>0</td>
									</tr>
									<tr>
										<td>Losses:</td>
										<td>0</td>
									</tr>
									<tr>
										<td>Matches:</td>
										<td>0</td>
									</tr>
									<tr>
										<td>Tournaments:</td>
										<td>0</td>
									</tr>
									</tbody>
								</Table>;
							</div>
						</Jumbotron>
					</div>
				</center>
			);
		}

	}
}

export default ProfileAboutPage;