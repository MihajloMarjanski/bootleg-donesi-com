Vue.component("allRestaurants",{

    data: function(){
        return{
            zoom: 15,
            center: [20.395587077688546, 45.38230342193068],
            location: [20.395587077688546, 45.38230342193068],
            rotation: 0,
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
            },

            user:{
                role:"",
                entityID:"",
            },
            myResId:"",
            myRes: false,
            commentable:false,
            commentReq:{
                username:"",
                entityID:"",
            },
            comment:{
                restaurant:"",
                customer:"",
                rating:1,
                text:"",
                username:""
            },
            cartItem:{
                entityID:"",
                menuItem:""
            }

        }
    },
    mounted(){
        this.user.entityID = localStorage.getItem("id")
        this.user.role = localStorage.getItem("role");
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
                        <th style="width:15%">Name</th>
                        <th style="width:5%">Type</th>
                        <th style="width:15%">Location</th>
                        <th style="width:5%">Rating(1 to 5)</th>
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
                    <div class="container">
                    <vl-map data-projection="EPSG:4326" style="height: 500px; width: 500px">
                    <vl-view :zoom.sync="zoom" :center.sync="center" :rotation.sync="rotation"></vl-view>
                        <vl-layer-tile>
                        <vl-source-osm></vl-source-osm>
                        </vl-layer-tile>
                                <vl-feature>
                        <vl-geom-point :coordinates="location">
                        </vl-geom-point>
                        <vl-style-box>
                            <vl-style-icon
                                src="/marker/marker.png"
                                :scale="0.05"
                                :anchor="[0.5, 1]"
                            ></vl-style-icon>
                        </vl-style-box>
                        </vl-feature>
                    </vl-map>
                    </div>
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
                                <td v-if="role === 'CUSTOMER' && restaurantDTO.status === 'OPEN' " style="width:5%"><input min="0" style="width:99%" type="number" v-model="i.count" placeholder = "Count"/><button type= "button" v-on:click="addToCart(i)">Add To Cart</button> </td>
                                <td v-if="role === 'CUSTOMER' && restaurantDTO.status === 'CLOSED' " style="width:5%"></td>
                            </tr>                                                                  
                        </tbody>
                    </table>            
                </div>
                <h1 v-if="commentable">Leave a comment</h1>
                <div v-if="commentable">
                    <input style="width:85%" type="text" v-model="comment.text" placeholder = "Comment"/>
                    <input :class="{invalid:comment.rating < 1 || comment.rating > 5}" min="1" max="5" style="width:5%" type="number" v-model="comment.rating" placeholder = "Rating"/>
                    <button type= "button" v-on:click="makeComment()">Make Comment</button>
                </div>
                <h1 v-if="!myRes">Comments</h1>
                <div v-if="!myRes">
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
                <div v-if="myRes">
                    <myComments></myComments>
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
                this.location[0] = this.restaurantDTO.location.geoLatitude
                this.location[1] = this.restaurantDTO.location.geoLongitute
                this.center = this.location
                if(this.role === 'MENAGER'){
                    axios
                    .post('/getMyRestaurant',this.user)
                    .then(response=>{
                        this.myResId = response.data
                        this.myRes = this.myResId == this.restaurantDTO.entityID
                    })
                }
                if(this.role === 'CUSTOMER'){
                    this.commentReq.username = localStorage.getItem("username")
                    this.commentReq.entityID = this.restaurantDTO.entityID
                    axios
                    .post('/canIComment',this.commentReq)
                    .then(response=>{
                        this.commentable = response.data
                        this.comment.customer = localStorage.getItem("id")
                        this.comment.username = localStorage.getItem("username")
                        this.comment.restaurant = this.restaurantDTO.entityID
                    })
                }
            })
            .catch((error) => {
              });
        },
        makeComment(){
            axios
            .post('/makeComment', this.comment)
            .then(response=>{
                axios
                .post('/viewRestaurant', this.reqparams)
                .then(response=>{
                    this.restaurantDTO = response.data
                    if(this.role === 'MENAGER'){
                        axios
                        .post('/getMyRestaurant',this.user)
                        .then(response=>{
                            this.myResId = response.data
                            this.myRes = this.myResId == this.restaurantDTO.entityID
                        })
                    }
                    if(this.role === 'CUSTOMER'){
                        this.commentReq.username = localStorage.getItem("username")
                        this.commentReq.entityID = this.restaurantDTO.entityID
                        axios
                        .post('/canIComment',this.commentReq)
                        .then(response=>{
                            this.commentable = response.data
                            this.comment.customer = localStorage.getItem("id")
                            this.comment.username = localStorage.getItem("username")
                            this.comment.restaurant = this.restaurantDTO.entityID
                        })
                    }
                })
                .catch((error) => {
                  });
            })
            .catch((error) => {
                console.log("Error");
                alert("Rating from 1 to 5");
              });
        },
        deleteComment(comment){

            axios
            .post('/deleteComment', comment)
            .then(response=>{
                axios
                .post('/viewRestaurant', this.reqparams)
                .then(response=>{
                    this.restaurantDTO = response.data
                })
                .catch((error) => {
                  });
            })
            .catch((error) => {
                console.log(error)
              });
        },
        deleteMenuItem(menuItem){
            axios
            .post('/deleteMenuItem', menuItem)
            .then(response=>{
                axios
                .post('/viewRestaurant', this.reqparams)
                .then(response=>{
                    this.restaurantDTO = response.data
                })
                .catch((error) => {
                  });
            })
            .catch((error) => {
                console.log(error)
              });
        },
        deleteRestaurant(restaurant){
            axios
            .post('/deleteRestaurant', restaurant)
            .then(response=>{
            })
            .catch((error) => {
              });
              axios
              .post('/searchRestaurants', this.searchParmas)
              .then(response=>{
                  this.restaurants = response.data
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
        addToCart(menuItem){
            if(menuItem.count >0){
                this.cartItem.entityID = localStorage.getItem("id")
                this.cartItem.menuItem = menuItem
                console.log(this.cartItem)
                axios
                .post('/addToCart', this.cartItem)
                .then(response=>{
                    menuItem.count = 0;
                    alert("Added to cart");
                })
                .catch((error) => {
                    menuItem.count = 0;
                  });
            }

        },
    }

})