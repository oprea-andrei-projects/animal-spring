import React, { useState } from "react";
import Api from "../../Api.js";

export default function CreateAnimal(){


    let [id,setId] = useState('');
    let [name, setName] = useState('');
    let [no, setNo] = useState('');



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



    let handleAddAnimal = async ()=>{


        let animal ={

            name,
            no

        }

        let api = new Api();

       let x =  await api.addAanimal(animal);
        
    }



    return(


        <>

            <form className="inputValues" onChange={handleChange}>

                <h2>Add Animal</h2>
                
                <label for="name">Animal name:</label>
                <input type="text" id="name" className="name" />

                <label for="noAnim">No of Animals:</label>
                <input type="text" id="noAnim" className="noAnim" />

                <input type="button" value="Insert" className="insert" onClick={handleAddAnimal}/>


            </form>
        
        
        
        </>
    )



}