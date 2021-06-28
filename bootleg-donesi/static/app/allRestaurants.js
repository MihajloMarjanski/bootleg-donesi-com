Vue.component("allRestaurants",{

    data: function(){
        return{
            restaurants:"",
            searchParmas:{
                name:"",
                location:"",
                rating:"",
                type:"",
                sort:"",
            },
            role:"",

        }
    },
    mounted(){
        this.role = localStorage.getItem("role");
        axios
        .get('/allRestaurants')
        .then(response=>{
            this.restaurants = response.data
        })
    },
    template:`
        <div>
            <h1>Restaurants</h1> 
            <div>
            <input type="text" v-model="searchParmas.name" placeholder="Name"/>
            <input type="text" v-model="searchParmas.location" placeholder="Location"/>
            <label for="rating"><b>Rating</b></label>
            <select name="rating" v-model="searchParmas.rating" id="rating">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
            <label for="type"><b>Type</b></label>
            <select name="type" v-model="searchParmas.type" id="type">
                <option value="ITALIAN">Italian</option>
                <option value="CHINESE">Chinese</option>
                <option value="GRILL">Grill</option>
                <option value="GREEK">Greek</option>
                <option value="MEXICAN">Mexican</option>
            </select>
            <label for="sort"><b>Sort by</b></label>
            <select name="sort" v-model="searchParmas.sort" id="sort">
                <option value="ASCNAME">Name Ascending</option>
                <option value="DESCNAME">Name Descending</option>
                <option value="ASCLOC">Location Ascending</option>
                <option value="DESCLOC">Location Descending</option>
                <option value="ASCRATING">Rating Ascending</option>
                <option value="DESCRATING">Rating Descending</option>
            </select>
            <button type="button" v-on:click="search">Filter</button>
        </div>
            <table style="width:99.999%">
                <thead>
                    <th style="width:5%">Logo</th>
                    <th style="width:10%">Name</th>
                    <th style="width:10%">Type</th>
                    <th style="width:10%">Location</th>
                    <th style="width:10%">Rating(1 to 5)</th>
                    <th v-if="role === 'ADMINISTRATOR'" style="width:5%"></th>
                </thead>
                <tbody>
                <tr v-for="r in restaurants" @click="view(r)">
                    <td style="width:15%" ><img :src="r.logoPath" width="100" height="100" ></td>
                   <td style="width:15%" >{{r.name}}</td>
                   <td style="width:10%">{{r.type}}</td>
                   <td style="width:15%">{{r.location.adress.street}}, {{r.location.adress.town}}, {{r.location.adress.country}}</td>
                   <td style="width:10%">{{r.rating}}</td>
                   <td v-if="role === 'ADMINISTRATOR'" style="width:5%"><button type= "button" v-on:click="deleteRestaurant(r)">Delete</button> </td>
                </tr>
               </tbody>
            </table>
        </div>
    `,
    methods:{
        view(restaurant){


        },
        deleteRestaurant(restaurant){
            axios
            .post('/deleteRestaurant', restaurant)
            .then(response=>{
                this.restaurants = response.data
                this.searchParmas.name = ""
                this.searchParmas.location = ""
                this.searchParmas.rating = ""
                this.searchParmas.sort = ""
                this.searchParmas.type = ""
            })
            .catch((error) => {
              });

        },
        search(){
            axios
            .post('/searchRestaurants', this.searchParmas)
            .then(response=>{
                this.restaurants = response.data
            })
            .catch((error) => {
              });
        },
    }

})