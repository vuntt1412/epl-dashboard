import './App.css';
import {BrowserRouter, Route, Routes} from 'react-router-dom'
import {TeamPage} from "./pages/TeamPage";

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route path="/teams/:teamName" element={<TeamPage/>}>
                    </Route>
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
