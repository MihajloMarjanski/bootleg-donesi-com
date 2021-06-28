Vue.component("allRestaurants",{

    data: function(){
        return{
            restaurants:"",

        }
    },
    mounted(){
        axios
        .get('/allRestaurants')
        .then(response=>{
            this.restaurants = response.data
        })
    },
    template:`
        <div>
            <h1>Restaurant list</h1> 
            <table style="width:100%">
                <thead>
                    <th style="width:5%">Logo</th>
                    <th style="width:10%">Name</th>
                    <th style="width:10%">Type</th>
                    <th style="width:10%">Location</th>
                    <th style="width:10%">Rating(1 to 5)</th>
                </thead>
                <tbody>
                <tr v-for="r in restaurants" @click="view(r)">
                    <td style="width:15%"><img :src="r.logoPath" width="100" height="100" ></td>
                   <td style="width:15%">{{r.name}}</td>
                   <td style="width:10%">{{r.type}}</td>
                   <td style="width:15%">{{r.location.adress.street}}, {{r.location.adress.town}}</td>
                   <td style="width:10%">{{r.rating}}</td>
                </tr>
               </tbody>
            </table>
        </div>
    `,
    methods:{
        view(restaurant){
            
        },
    }

})