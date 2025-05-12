import TitleRigth from "../TitleTopic/TitleRight";
const OurBlog = () => {
  return (
    <div className="bg-white text-dark">
      <div className="container py-5">
        <TitleRigth
          title="Our Blog"
          content="An insight the incredible experience in the world."
          nonePage={true}
        ></TitleRigth>

        <div className="row align-items-center">
          <div className="col-md-6 mb-4 mb-md-0">
            <img
              src="https://storage.googleapis.com/a1aa/image/Qrlcs5S0KaTUnYTBTxYp3-Etb-jZ91qpBrbfDj6lg8g.jpg"
              alt="A beautiful coastal town in Italy with colorful buildings on a cliff by the sea"
              className="img-fluid rounded"
            />
          </div>
          <div className="col-md-6">
            <h2 className="display-6 fw-bold mb-4">
              Beautiful Italy
              <br />
              Let's travel
            </h2>
            <p className="text-muted mb-4">
              But I must explain to you how all this mistaken idea of denouncing
              pleasure and praising pain was born and I will give you a complete
              account of the system and expound the actual teachings of the
              great explorer of the truth, the master- builder of human
              happiness. No one rejects, dislike, or avoids pleasure itself,
              because it is pleasure, but because those who do not know how to
              pursue pleasure rationally encounter consequences that are
              extremely painful. Nor again is there anyone who loves or pursues.
            </p>
            <a href="#" className="text-danger fw-medium">
              Read More <i className="fas fa-arrow-right"></i>
            </a>
          </div>
        </div>
      </div>
    </div>
  );
};
export default OurBlog;
