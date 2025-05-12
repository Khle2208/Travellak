import TitleLeft from "../TitleTopic/TitleLeft";
import Founder from "../../../assets/image/Founder.png";
const AboutUs = () => {
  return (
    <div className="bg-white text-dark">
      <div className="container py-5">
        <TitleLeft
          title="About Us"
          content="The people behind Trevolak"
          nonePage={true}
        ></TitleLeft>

        <div className="row align-items-center">
          <div className="col-md-6 mb-4 mb-md-0  d-flex justify-content-center">
            <img
              src={Founder}
              alt="A beautiful coastal town in Italy with colorful buildings on a cliff by the sea"
              className="img-fluid rounded"
            />
          </div>
          <div className="col-md-6">
            <h2 className="display-6 fw-bold mb-4">Khoa Lê</h2>
            <p className="display-6 fw-bold mb-4">Role: Lead Web Developer</p>
            <p className="text-muted mb-4">
              As the lead web developer, I am responsible for designing, coding,
              and building the entire website from scratch. I bring innovative
              ideas to life by creating responsive, user-friendly, and
              high-performance web solutions that not only engage visitors but
              also deliver meaningful content. With a deep understanding of
              modern development frameworks and best practices, I ensure that
              each component of the site is optimized for seamless interactions
              and enhanced user experience. My expertise spans both front-end
              and back-end development, allowing me to create robust digital
              platforms that serve as the backbone of our online presence.
            </p>
          </div>
        </div>

        <div className="row align-items-center mt-4">
          <div className="col-md-6">
            <h2 className="display-6 fw-bold mb-4">Khoa Lê</h2>
            <p className="display-6 fw-bold mb-4">Role: Lead Web Developer</p>
            <p className="text-muted mb-4">
              As the lead web developer, I am responsible for designing, coding,
              and building the entire website from scratch. I bring innovative
              ideas to life by creating responsive, user-friendly, and
              high-performance web solutions that not only engage visitors but
              also deliver meaningful content. With a deep understanding of
              modern development frameworks and best practices, I ensure that
              each component of the site is optimized for seamless interactions
              and enhanced user experience. My expertise spans both front-end
              and back-end development, allowing me to create robust digital
              platforms that serve as the backbone of our online presence.
            </p>
          </div>

          <div className="col-md-6 mb-4 mb-md-0  d-flex justify-content-center">
            <img
              src={Founder}
              alt="A beautiful coastal town in Italy with colorful buildings on a cliff by the sea"
              className="img-fluid rounded"
            />
          </div>
        </div>
      </div>
    </div>
  );
};
export default AboutUs;
