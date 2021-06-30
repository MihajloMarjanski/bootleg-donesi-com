Vue.component("allRestaurants",{

    data: function(){
        return{
            restaurants:"",
            searchParmas:{
                name:"",
                location:"",
                rating:"",
                type:"",
                open:"",
                sort:"",
            },
            role:"",
            restaurantDTO:"",
            reqparams:{
                role:"",
                entityID:"",
            }
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
            <div v-if="!restaurantDTO">
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
                    <label for="open"><b>Open</b></label>
                    <select name="open" v-model="searchParmas.open" id="open">
                        <option value="OPEN">Yes</option>
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
                       <td v-if="!r.rating" style="width:10%">No rating yet</td>
                       <td v-if="r.rating" style="width:10%">{{r.rating}}</td>
                       <td v-if="role === 'ADMINISTRATOR'" style="width:5%"><button type= "button" v-on:click="deleteRestaurant(r)">Delete</button> </td>
                    </tr>
                   </tbody>
                </table>
            </div>
            <div v-if="restaurantDTO">
                <h1>Information</h1>
                <div>
                    <table style="width:99.999%">
                        <thead>
                            <th style="width:50%"></th>
                            <th style="width:50%"></th>
                        </thead>
                        <tbody>
                        <tr class="inforow">
                            <td style="width:50%; text-align:center"><b>Logo</b></td>
                            <td style="width:50%"><img :src="restaurantDTO.logoPath" width="100" height="100" ></td>
                        </tr>                        
                        <tr class="inforow">
                            <td style="width:50%; text-align:center"><b> Name</b></td>
                            <td style="width:50%">{{restaurantDTO.name}} </td>
                        </tr>
                        <tr class="inforow">
                            <td style="width:50%; text-align:center"><b>Type</b></td>
                            <td style="width:50%">{{restaurantDTO.type}}  </td>
                        </tr>
                        <tr class="inforow">
                            <td style="width:50%; text-align:center"><b>Status</b></td>
                            <td style="width:50%">{{restaurantDTO.status}}  </td>
                        </tr>
                        <tr class="inforow">
                            <td style="width:50%; text-align:center"><b>Rating</b></td>
                            <td style="width:50%" v-if="!restaurantDTO.rating" >No rating yet</td>
                            <td style="width:50%" v-if="restaurantDTO.rating" >{{restaurantDTO.rating}}</td>
                        </tr> 
                        <tr class="inforow">
                            <td style="width:50%; text-align:center"><b>Location</b></td>
                            <td style="width:50%">{{restaurantDTO.location.adress.street}}, {{restaurantDTO.location.adress.town}}, {{restaurantDTO.location.adress.country}}</td>
                        </tr>                                            
                        </tbody>
                    </table>
                </div>
                <h1>Menu</h1>
                <div>
                    <table style="width:99.999%">
                        <thead>
                            <th style="width:20%">Picture</th>
                            <th style="width:15%">Name</th>
                            <th style="width:40%">Description</th>
                            <th style="width:5%">Price</th>
                            <th v-if="role === 'ADMINISTRATOR'" style="width:5%"></th>
                            <th v-if="role === 'CUSTOMER'" style="width:5%"></th>
                        </thead>
                        <tbody>
                            <tr class="nopointerrow" v-for="i in restaurantDTO.menuItems">
                                <td style="width:20%"> <img :src="i.picturePath" width="75" height="75" ></td>
                                <td style="width:15%">{{i.name}}</td>
                                <td style="width:40%">{{i.description}}</td>
                                <td style="width:5%">{{i.price}}</td>
                                <td v-if="role === 'ADMINISTRATOR'" style="width:5%"><button type= "button" v-on:click="deleteMenuItem(i)">Delete</button> </td>
                            </tr>                                                                  
                        </tbody>
                    </table>            
                </div>
                <h1>Comments</h1>
                <div>
                    <table style="width:99.999%">
                        <thead>
                            <th style="width:33%">Username</th>
                            <th style="width:60%">Comment</th>
                            <th style="width:5%">Rating</th>
                            <th v-if="role === 'ADMINISTRATOR'" style="width:5%"></th>
                        </thead>
                        <tbody>
                            <tr class="nopointerrow" v-for="c in restaurantDTO.comments">
                                <td style="width:33%"> {{c.username}}</td>
                                <td style="width:60%">{{c.text}}</td>
                                <td style="width:5%">{{c.rating}}</td>
                                <td v-if="role === 'ADMINISTRATOR'" style="width:5%"><button type= "button" v-on:click="deleteComment(c)">Delete</button> </td>
                            </tr>                                                                  
                        </tbody>
                    </table>            
                </div>
            </div>
        </div>
    `,
    methods:{
        view(restaurant){
            this.reqparams.role = this.role
            this.reqparams.entityID = restaurant.entityID
            axios
            .post('/viewRestaurant', this.reqparams)
            .then(response=>{
                this.restaurantDTO = response.data
            })
            .catch((error) => {
              });
        },
        deleteComment(comment){

        },
        deleteMenuItem(menuItem){

        },
        deleteRestaurant(restaurant){
            axios
            .post('/deleteRestaurant', restaurant)
            .then(response=>{
                axios
                .post('/searchRestaurants', this.searchParmas)
                .then(response=>{
                    this.restaurants = response.data
                })
                .catch((error) => {
                  });
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