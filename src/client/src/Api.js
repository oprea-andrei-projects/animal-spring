export default class Api{



    api(path, method='GET', body=null){
 
         const url = "http://localhost:8080/" + path;
 
         const options = {
 
                 method,
                 headers:{
                     'Content-Type':'application/json; charset=utf-8',
                     'X-Requested-With': 'XMLHttpRequest'
 
 
                 },
 
         };
 
         if(body!==null){
             options.body = JSON.stringify(body);
         }
 
         return fetch(url, options);
 
    }

 
    async getAnimals(){

        return this.api("api/v1/animal/allAnimals").then(data=>data.json());
    }


    async addAanimal(animal){

        let data = await this.api('api/v1/animal/addAnimal','POST', animal);

        let data2 = await data.json();

        console.log(data);
    }

    async updateTheAnimal(animal){

        let data = await this.api(`api/v1/animal/updateAnimal`,'PUT',animal);

        let data2 = await data.json();

        console.log(data2);



    }

    async deleteTheAnimal(id){


        let data = await this.api(`api/v1/animal/deleteAnim/${id}`,`DELETE`);
        let data2 = await data.json();

    }
 }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 