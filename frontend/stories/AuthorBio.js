import React from "react";
import {
  Box,
  Typography,
  Button,
  ButtonBase,
  Modal,
  TextField,
  InputAdornment,
} from "@mui/material";
import {
  styled,
  createTheme,
  responsiveFontSizes,
  ThemeProvider,
} from "@mui/material/styles";

let theme = createTheme();
theme = responsiveFontSizes(theme);

const Img = styled("img")({
  margin: "auto",
  display: "block",
  maxWidth: "100%",
  maxHeight: "100%",
});

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  pt: 2,
  px: 4,
  pb: 3,
};

function SponsorModal({ sponsorText, onSponsorSubmit }) {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };
  const [sponsorAmount, setSponsorAmount] = React.useState();

  return (
    <>
      <Button variant="contained" aria-label="sponsor" onClick={handleOpen}>
        Sponsor
      </Button>
      <Modal open={open} onClose={handleClose}>
        <Box
          sx={{
            ...style,
            width: 400,
            display: "flex",
            flexDirection: "column",
            gap: 1,
          }}
        >
          <h2 id="sponsorship-modal">Sponsor</h2>
          <b1>{sponsorText}</b1>
          <TextField
            id="sponsor amount"
            label="Sponsor Amount"
            value={sponsorAmount}
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">$</InputAdornment>
              ),
            }}
            onChange={(evt) =>
              setSponsorAmount(evt.target.value.replace(/\D/g, ""))
            }
          />
          <Button
            variant="contained"
            onClick={() => {
              onSponsorSubmit(sponsorAmount), handleClose(handleClose);
            }}
          >
            Submit
          </Button>
          <Button variant="outlined" onClick={handleClose}>
            Cancel
          </Button>
        </Box>
      </Modal>
    </>
  );
}

const AuthorBio = ({
  username,
  bioText,
  wordcountText,
  publicReviewsText,
  publicRatingsText,
  image,
  isOwner,
  onFollow,
  onSponsor,
  sponsorText,
  onSponsorSubmit,
  onEditAvatar,
}) => {
  return (
    <div>
      <ThemeProvider theme={theme}>
        <Box
          sx={{
            display: "grid",
            borderRadius: 2,
            boxShadow: 3,
            gridTemplateColumns: "auto",
            gap: 1,
            p: { xs: 0, sm: 2 },
            gridTemplateRows: "auto",
            gridTemplateAreas: {
              xs: `"avatar avatar username username"
                   "avatar avatar follow sponsor"
                   "stats  stats  bio    bio"
                   "stats  stats  bio    bio"`,
              sm: `"avatar username username follow sponsor"
                   "avatar bio      bio      bio    bio"
                   "stats  bio      bio      bio    bio"`,
            },
          }}
        >
          <Box
            sx={{
              gridArea: "avatar",
              display: "flex",
              alignItems: "center",
              justifyContent: "start",
            }}
          >
            <ButtonBase
              onClick={onEditAvatar}
              sx={{
                width: "auto",
                height: "auto",
                maxWidth: { xs: 150, sm: 175 },
                maxHeight: { xs: 150, sm: 175 },
              }}
            >
              <Img alt="default" src={image} />
            </ButtonBase>
          </Box>
          <Box
            sx={{
              gridArea: "username",
              display: "inline-flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <Typography variant="h4">{username}</Typography>
          </Box>
          <Box
            sx={{
              gridArea: "follow",
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <Button variant="contained" aria-label="follow" onClick={onFollow}>
              Follow
            </Button>
          </Box>
          <Box
            sx={{
              gridArea: "sponsor",
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            {isOwner ? (
              <Button variant="contained" disabled>
                Sponsor
              </Button>
            ) : (
              <SponsorModal
                sponsorText={sponsorText}
                onSponsorSubmit={onSponsorSubmit}
              />
            )}
          </Box>
          <Box
            sx={{
              gridArea: "bio",
              display: "flex",
              alignItems: "flex-start",
            }}
          >
            <Typography variant="b1">{bioText}</Typography>
          </Box>
          <Box
            sx={{
              gridArea: "stats",
              display: "flex",
              flexDirection: "column",
              lineHeight: 1,
            }}
          >
            <Typography variant="b1">{wordcountText}</Typography>
            <Typography variant="b1">{publicReviewsText}</Typography>
            <Typography variant="b1">{publicRatingsText}</Typography>
          </Box>
        </Box>
      </ThemeProvider>
    </div>
  );
};

export default AuthorBio;
