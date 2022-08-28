import React, { useState, useEffect } from "react";
import Api from "../../Api.js";
import Animal from "./Animal.js"
import Spinner from 'react-bootstrap/Spinner';
import { useNavigate } from "react-router-dom"

function Home() {

    let [animals, setAnimals] = useState([]);


    let fetchAnimals = async () => {


        let api = new Api();

        let animalArray = await api.getAnimals();

        console.log("fetch");
        setAnimals(animalArray);

    }

    useEffect(() => {

        fetchAnimals();

    }, []);


    return (
        <>

            <input type="button" value="Order A->Z" id="order" className="order" />

            <label for="animNo">Enter number of animals: </label>
            <input type="text" id="animNo" className="animNo" />
            <input type="button" value="Search" id="search" className="search" />

            <table>

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Animal Name</th>
                        <th>Number of Animals</th>
                    </tr>

                </thead>

                <tbody className="tableBody">

                    {
                        animals.length == 0
                            ?
                            (


                                <Spinner animation="border" role="status">
                                    <span className="visually-hidden">Loading...</span>
                                </Spinner>

                               


                            )
                            :
                            (
                                animals.map(element => {
                                    return <Animal animal={element} />
                                })
                            )






                    }


                </tbody>

            </table>

        </>)
}
export default Home;