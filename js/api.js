// ===== API BASE URL =====
const API = 'http://localhost:8080/api';
// const tokens=sessionStorage.getItem("token");
// if(tokens){
//     headers: {
//         'Authorization': `Bearer ${tokens}`,
//         "COntent-Type": "application/json"
//     }
// }
// ===== GENERIC FETCH HELPERS =====
async function apiGet(endpoint) {
    const res = await fetch(`${API}${endpoint}`);
    if (!res.ok) {
        const err = await res.json().catch(() => ({ message: res.statusText }));
        throw new Error(err.message || 'Request failed');
    }
    return await res.json();
}

async function apiPost(endpoint, data) {
    const res = await fetch(`${API}${endpoint}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    });
    if (!res.ok) {
        const err = await res.json().catch(() => ({ message: res.statusText }));
        throw new Error(err.message || 'Request failed');
    }
    return res.json();
}

async function apiPut(endpoint, data) {
    const res = await fetch(`${API}${endpoint}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: data ? JSON.stringify(data) : undefined
    });
    if (!res.ok) {
        const err = await res.json().catch(() => ({ message: res.statusText }));
        throw new Error(err.message || 'Request failed');
    }
    return res.json();
}

async function apiDelete(endpoint) {
    const res = await fetch(`${API}${endpoint}`, { method: 'DELETE' });
    if (!res.ok) {
        const err = await res.json().catch(() => ({ message: res.statusText }));
        throw new Error(err.message || 'Request failed');
    }
    // might return text
    const text = await res.text();
    try { return JSON.parse(text); } catch { return text; }
}

// ===== USER APIs =====
const UserAPI = {
    sendOtp: (data) => apiPost('/users/send-otp', data),
    verifyOtp: (data) => apiPost('/users/verify-otp', data),
    register: (data) => apiPost('/users/register', data),
    login: (data) => apiPost('/users/login', data),
    getAll: () => apiGet('/users'),
    getById: (id) => apiGet(`/users/${id}`)
};

const AdminAPI = {
    login: (data) => apiPost('/admin/login', data),
    addMovie: (data) => apiPost('/admin/movies', data),
    addTheater: (data) => apiPost('/admin/theaters', data),
    addScreen: (data) => apiPost('/admin/screens', data),
    addShow: (data) => apiPost('/admin/shows', data),
    // getAll: () => apiGet('/users'),
    // getById: (id) => apiGet(`/users/${id}`)

};

// ===== CITY APIs =====
const CityAPI = {
    add: (data) => apiPost('/cities', data),
    getAll: () => apiGet('/cities'),
    getById: (id) => apiGet(`/cities/${id}`)
};

// ===== MOVIE APIs =====
const MovieAPI = {
    add: (data) => apiPost('/movies', data),
    getAll: () => apiGet('/movies'),
    getById: (id) => apiGet(`/movies/${id}`),
    search: (title) => apiGet(`/movies/search?title=${encodeURIComponent(title)}`),
    getByGenre: (genre) => apiGet(`/movies/genre/${genre}`),
    getByLanguage: (lang) => apiGet(`/movies/language/${lang}`),
    update: (id, data) => apiPut(`/movies/${id}`, data),
    delete: (id) => apiDelete(`/movies/${id}`)
};

const TheaterAPI = {
    add: (data) => apiPost('/theaters', data),
    getAll: () => apiGet('/theaters'),
    getById: (id) => apiGet(`/theaters/${id}`),
    getByCity: (cityId) => apiGet(`/theaters/city/${cityId}`),
    getByAdmin: (adminId) => apiGet(`/theaters/admin/${adminId}`)
};

// ===== SCREEN APIs =====
const ScreenAPI = {
    add: (data) => apiPost('/screens/addScreen', data),
    getAll: () => apiGet('/screens'),
    getById: (id) => apiGet(`/screens/${id}`),
    getByTheater: (theaterId) => apiGet(`/screens/theater/${theaterId}`)
};

// ===== SEAT APIs =====
const SeatAPI = {
    add: (data) => apiPost('/seats', data),
    getByScreen: (screenId) => apiGet(`/seats/screen/${screenId}`),
    getById: (id) => apiGet(`/seats/${id}`)
};

const ShowSeatAPI = {
    
    getByShow: (showId) => apiGet(`/show-seats/${showId}`),
    lockSeats: (data) => apiPost('/show-seats/lock', data),
    releaseSeats: (data) => apiPost('/show-seats/release', data)
};

// ===== SHOW APIs =====
const ShowAPI = {
    add: (data) => apiPost('/shows/addshow', data),
    getAll: () => apiGet('/shows'),
    getById: (id) => apiGet(`/shows/${id}`),
    getByScreenId: (sid) => apiGet(`/shows/screen/${sid}`),
    getByMovie: (movieId) => apiGet(`/shows/movie/${movieId}`),
    getByMovieAndDate: (movieId, date) => apiGet(`/shows/movie/${movieId}/date?date=${date}`)
};
const paymentAPI = {
    processPayment: (data) => apiPost('/payments/process', data),
    verify: (data) => apiPost('/payments/verify', data)

};

const BookingAPI = {
    create: (data) => apiPost('/bookings/confirm', data),
    getById: (id) => apiGet(`/bookings/${id}`),
    getByUser: (userId) => apiGet(`/bookings/user/${userId}`),
    cancel: (id) => apiPut(`/bookings/cancel/${id}`),
    getAvailableSeats: (showId) => apiGet(`/bookings/show/${showId}/available-seats`)
};
