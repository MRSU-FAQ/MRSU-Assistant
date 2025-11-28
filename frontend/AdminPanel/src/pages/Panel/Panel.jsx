import Questions from "./components/Questions/Questions.jsx";

export default function Panel() {
  return(
    <div className={"panel"}>
      <div className={"panel-header"}>
        {/*Кнопку застилизуй, но не делай с ней ничего функционального, буду сам делать*/}
        <button>Выход</button>
      </div>
      <div className={"panel-body"}>
        <div className={"panel-question"}>
          <Questions/>
        </div>
      </div>
    </div>
  )
}
