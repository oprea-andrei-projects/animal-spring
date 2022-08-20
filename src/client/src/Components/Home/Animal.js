import React, { useEffect } from "react";

function Animal({animal}){

    useEffect(()=>{

        console.log("aici");

    },[]);


    return(


                <tr>
            
                    <td>{animal.id}</td>
                    <td>{animal.name}</td>
                    <td>{animal.no}</td>
    
                </tr>


    );
}

export default Animal;