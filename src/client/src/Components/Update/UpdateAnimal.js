import React, { useState } from "react";
import Api from "../../Api.js";

export default function UpdateAnimal(){


    let [id,setId] = useState(35);
    let [name, setName] = useState('name9');
    let [no, setNo] = useState(2);


    let handleChange = (e)=>{

        let obj = e.target;

        if(obj.classList.contains('name')){

            setName(obj.value);

            console.log(name);
        }

        if(obj.classList.contains('noAnim')){

            setNo(obj.value);

            console.log(no);
        }

    }

    let handleUpdate = async ()=>{


        let animal = {
            id,
            name,
            no
        }

        let api = new Api();

        await api.updateTheAnimal(animal);
    }

    let handleDelete = async ()=>{

        let animal = {
            id,
            name,
            no
        }

        let api = new Api();

        await api.deleteTheAnimal(animal.id);


    }


    return(
        <>

            <form className="updateValues" onChange={handleChange}>

                <h2>Update Animal</h2>

                <label for="name">Animal name:</label>
                <input type="text" id="name" className="name" value={name} />

                <label for="noAnim">No of Animals:</label>
                <input type="text" id="noAnim" className="noAnim" value={no} />


                <input type="button" value="Update" className="updateBtn" onClick={handleUpdate} />

                <input type="button" value="Delete" className="deleteBtn" onClick={handleDelete} />

            </form>


        </>
    )

}