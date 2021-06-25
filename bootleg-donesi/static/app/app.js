const Home = { template: '<home></home>' }
const Register = { template: '<register></register>' }

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Home},
		{ path: '/register', name: 'register', component: Register}
	  ]
});

var app = new Vue({
	router,
	el: '#app'
});