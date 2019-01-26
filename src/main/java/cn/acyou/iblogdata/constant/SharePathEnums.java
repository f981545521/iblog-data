package cn.acyou.iblogdata.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分享路径
 * @author youfang
 * @version [1.0.0, 2019-01-15 上午 10:15]
 **/
public enum SharePathEnums {
    /* 路径 */
    INVITE("/home/index"),
    BARGAIN("/mkt/bargain"),
    BARGAIN2("/mkt/bargain2"),
    BARGAIN3("/mkt/bargain3"),
    BARGAIN4("/mkt/bargain4"),
    BARGAIN5("/mkt/bargain5"),
    BARGAIN6("/mkt/bargain6"),
    BARGAIN7("/mkt/bargain7"),
    BARGAIN8("/mkt/bargain8"),
    BARGAIN9("/mkt/bargain9"),
    BARGAIN10("/mkt/bargain10"),
    BARGAIN11("/mkt/bargain11"),
    BARGAIN12("/mkt/bargain12"),
    BARGAIN13("/mkt/bargain13"),
    BARGAIN14("/mkt/bargain14"),
    BARGAIN15("/mkt/bargain15"),
    BARGAIN16("/mkt/bargain16"),
    BARGAIN17("/mkt/bargain17"),
    BARGAIN18("/mkt/bargain18"),
    BARGAIN19("/mkt/bargain19"),
    BARGAIN20("/mkt/bargain20"),
    BARGAIN21("/mkt/bargain21"),
    BARGAIN22("/mkt/bargain22"),
    BARGAIN23("/mkt/bargain23"),
    BARGAIN24("/mkt/bargain24"),
    BARGAIN25("/mkt/bargain25"),
    BARGAIN26("/mkt/bargain26"),
    BARGAIN27("/mkt/bargain27"),
    BARGAIN28("/mkt/bargain28"),
    BARGAIN29("/mkt/bargain29"),
    BARGAIN30("/mkt/bargain30"),
    BARGAIN31("/mkt/bargain31"),
    BARGAIN32("/mkt/bargain32"),
    BARGAIN33("/mkt/bargain33"),
    BARGAIN34("/mkt/bargain34"),
    BARGAIN35("/mkt/bargain35"),
    BARGAIN36("/mkt/bargain36"),
    BARGAIN37("/mkt/bargain37"),
    BARGAIN38("/mkt/bargain38"),
    BARGAIN39("/mkt/bargain39"),
    BARGAIN40("/mkt/bargain40"),
    BARGAIN41("/mkt/bargain41"),
    BARGAIN42("/mkt/bargain42"),
    BARGAIN43("/mkt/bargain43"),
    BARGAIN44("/mkt/bargain44"),
    BARGAIN45("/mkt/bargain45"),
    BARGAIN46("/mkt/bargain46"),
    BARGAIN47("/mkt/bargain47"),
    BARGAIN48("/mkt/bargain48"),
    BARGAIN49("/mkt/bargain49"),
    BARGAIN50("/mkt/bargain50"),
    BARGAIN51("/mkt/bargain51"),
    BARGAIN52("/mkt/bargain52"),
    BARGAIN53("/mkt/bargain53"),
    BARGAIN54("/mkt/bargain54"),
    BARGAIN55("/mkt/bargain55"),
    BARGAIN56("/mkt/bargain56"),
    BARGAIN57("/mkt/bargain57"),
    BARGAIN58("/mkt/bargain58"),
    BARGAIN59("/mkt/bargain59"),
    BARGAIN60("/mkt/bargain60"),
    BARGAIN61("/mkt/bargain61"),
    BARGAIN62("/mkt/bargain62"),
    BARGAIN63("/mkt/bargain63"),
    BARGAIN64("/mkt/bargain64"),
    BARGAIN65("/mkt/bargain65"),
    BARGAIN66("/mkt/bargain66"),
    BARGAIN67("/mkt/bargain67"),
    BARGAIN68("/mkt/bargain68"),
    BARGAIN69("/mkt/bargain69"),
    BARGAIN70("/mkt/bargain70"),
    BARGAIN71("/mkt/bargain71"),
    BARGAIN72("/mkt/bargain72"),
    BARGAIN73("/mkt/bargain73"),
    BARGAIN74("/mkt/bargain74"),
    BARGAIN75("/mkt/bargain75"),
    BARGAIN76("/mkt/bargain76"),
    BARGAIN77("/mkt/bargain77"),
    BARGAIN78("/mkt/bargain78"),
    BARGAIN79("/mkt/bargain79"),
    BARGAIN80("/mkt/bargain80"),
    BARGAIN81("/mkt/bargain81"),
    BARGAIN82("/mkt/bargain82"),
    BARGAIN83("/mkt/bargain83"),
    BARGAIN84("/mkt/bargain84"),
    BARGAIN85("/mkt/bargain85"),
    BARGAIN86("/mkt/bargain86"),
    BARGAIN87("/mkt/bargain87"),
    BARGAIN88("/mkt/bargain88"),
    BARGAIN89("/mkt/bargain89"),
    BARGAIN90("/mkt/bargain90"),
    BARGAIN91("/mkt/bargain91"),
    BARGAIN92("/mkt/bargain92"),
    BARGAIN93("/mkt/bargain93"),
    BARGAIN94("/mkt/bargain94"),
    BARGAIN95("/mkt/bargain95"),
    BARGAIN96("/mkt/bargain96"),
    BARGAIN97("/mkt/bargain97"),
    BARGAIN98("/mkt/bargain98"),
    BARGAIN99("/mkt/bargain99"),
    BARGAIN100("/mkt/bargain100"),
    BARGAIN101("/mkt/bargain101"),
    BARGAIN102("/mkt/bargain102"),
    BARGAIN103("/mkt/bargain103"),
    BARGAIN104("/mkt/bargain104"),
    BARGAIN105("/mkt/bargain105"),
    BARGAIN106("/mkt/bargain106"),
    BARGAIN107("/mkt/bargain107"),
    BARGAIN108("/mkt/bargain108"),
    BARGAIN109("/mkt/bargain109"),
    BARGAIN110("/mkt/bargain110"),
    BARGAIN111("/mkt/bargain111"),
    BARGAIN112("/mkt/bargain112"),
    BARGAIN113("/mkt/bargain113"),
    BARGAIN114("/mkt/bargain114"),
    BARGAIN115("/mkt/bargain115"),
    BARGAIN116("/mkt/bargain116"),
    BARGAIN117("/mkt/bargain117"),
    BARGAIN118("/mkt/bargain118"),
    BARGAIN119("/mkt/bargain119"),
    BARGAIN120("/mkt/bargain120"),
    BARGAIN121("/mkt/bargain121"),
    BARGAIN122("/mkt/bargain122"),
    BARGAIN123("/mkt/bargain123"),
    BARGAIN124("/mkt/bargain124"),
    BARGAIN125("/mkt/bargain125"),
    BARGAIN126("/mkt/bargain126"),
    BARGAIN127("/mkt/bargain127"),
    BARGAIN128("/mkt/bargain128"),
    BARGAIN129("/mkt/bargain129"),
    BARGAIN130("/mkt/bargain130"),
    BARGAIN131("/mkt/bargain131"),
    BARGAIN132("/mkt/bargain132"),
    BARGAIN133("/mkt/bargain133"),
    BARGAIN134("/mkt/bargain134"),
    BARGAIN135("/mkt/bargain135"),
    BARGAIN136("/mkt/bargain136"),
    BARGAIN137("/mkt/bargain137"),
    BARGAIN138("/mkt/bargain138"),
    BARGAIN139("/mkt/bargain139"),
    BARGAIN140("/mkt/bargain140"),
    BARGAIN141("/mkt/bargain141"),
    BARGAIN142("/mkt/bargain142"),
    BARGAIN143("/mkt/bargain143"),
    BARGAIN144("/mkt/bargain144"),
    BARGAIN145("/mkt/bargain145"),
    BARGAIN146("/mkt/bargain146"),
    BARGAIN147("/mkt/bargain147"),
    BARGAIN148("/mkt/bargain148"),
    BARGAIN149("/mkt/bargain149"),
    BARGAIN150("/mkt/bargain150"),
    BARGAIN151("/mkt/bargain151"),
    BARGAIN152("/mkt/bargain152"),
    BARGAIN153("/mkt/bargain153"),
    BARGAIN154("/mkt/bargain154"),
    BARGAIN155("/mkt/bargain155"),
    BARGAIN156("/mkt/bargain156"),
    BARGAIN157("/mkt/bargain157"),
    BARGAIN158("/mkt/bargain158"),
    BARGAIN159("/mkt/bargain159"),
    BARGAIN160("/mkt/bargain160"),
    BARGAIN161("/mkt/bargain161"),
    BARGAIN162("/mkt/bargain162"),
    BARGAIN163("/mkt/bargain163"),
    BARGAIN164("/mkt/bargain164"),
    BARGAIN165("/mkt/bargain165"),
    BARGAIN166("/mkt/bargain166"),
    BARGAIN167("/mkt/bargain167"),
    BARGAIN168("/mkt/bargain168"),
    BARGAIN169("/mkt/bargain169"),
    BARGAIN170("/mkt/bargain170"),
    BARGAIN171("/mkt/bargain171"),
    BARGAIN172("/mkt/bargain172"),
    BARGAIN173("/mkt/bargain173"),
    BARGAIN174("/mkt/bargain174"),
    BARGAIN175("/mkt/bargain175"),
    BARGAIN176("/mkt/bargain176"),
    BARGAIN177("/mkt/bargain177"),
    BARGAIN178("/mkt/bargain178"),
    BARGAIN179("/mkt/bargain179"),
    BARGAIN180("/mkt/bargain180"),
    BARGAIN181("/mkt/bargain181"),
    BARGAIN182("/mkt/bargain182"),
    BARGAIN183("/mkt/bargain183"),
    BARGAIN184("/mkt/bargain184"),
    BARGAIN185("/mkt/bargain185"),
    BARGAIN186("/mkt/bargain186"),
    BARGAIN187("/mkt/bargain187"),
    BARGAIN188("/mkt/bargain188"),
    BARGAIN189("/mkt/bargain189"),
    BARGAIN190("/mkt/bargain190"),
    BARGAIN191("/mkt/bargain191"),
    BARGAIN192("/mkt/bargain192"),
    BARGAIN193("/mkt/bargain193"),
    BARGAIN194("/mkt/bargain194"),
    BARGAIN195("/mkt/bargain195"),
    BARGAIN196("/mkt/bargain196"),
    BARGAIN197("/mkt/bargain197"),
    BARGAIN198("/mkt/bargain198"),
    BARGAIN199("/mkt/bargain199"),
    BARGAIN200("/mkt/bargain200"),
    BARGAIN201("/mkt/bargain201"),
    BARGAIN202("/mkt/bargain202"),
    BARGAIN203("/mkt/bargain203"),
    BARGAIN204("/mkt/bargain204"),
    BARGAIN205("/mkt/bargain205"),
    BARGAIN206("/mkt/bargain206"),
    BARGAIN207("/mkt/bargain207"),
    BARGAIN208("/mkt/bargain208"),
    BARGAIN209("/mkt/bargain209"),
    BARGAIN210("/mkt/bargain210"),
    BARGAIN211("/mkt/bargain211"),
    BARGAIN212("/mkt/bargain212"),
    BARGAIN213("/mkt/bargain213"),
    BARGAIN214("/mkt/bargain214"),
    BARGAIN215("/mkt/bargain215"),
    BARGAIN216("/mkt/bargain216"),
    BARGAIN217("/mkt/bargain217"),
    BARGAIN218("/mkt/bargain218"),
    BARGAIN219("/mkt/bargain219"),
    BARGAIN220("/mkt/bargain220"),
    BARGAIN221("/mkt/bargain221"),
    BARGAIN222("/mkt/bargain222"),
    BARGAIN223("/mkt/bargain223"),
    BARGAIN224("/mkt/bargain224"),
    BARGAIN225("/mkt/bargain225"),
    BARGAIN226("/mkt/bargain226"),
    BARGAIN227("/mkt/bargain227"),
    BARGAIN228("/mkt/bargain228"),
    BARGAIN229("/mkt/bargain229"),
    BARGAIN230("/mkt/bargain230"),
    BARGAIN231("/mkt/bargain231"),
    BARGAIN232("/mkt/bargain232"),
    BARGAIN233("/mkt/bargain233"),
    BARGAIN234("/mkt/bargain234"),
    BARGAIN235("/mkt/bargain235"),
    BARGAIN236("/mkt/bargain236"),
    BARGAIN237("/mkt/bargain237"),
    BARGAIN238("/mkt/bargain238"),
    BARGAIN239("/mkt/bargain239"),
    BARGAIN240("/mkt/bargain240"),
    BARGAIN241("/mkt/bargain241"),
    BARGAIN242("/mkt/bargain242"),
    BARGAIN243("/mkt/bargain243"),
    BARGAIN244("/mkt/bargain244"),
    BARGAIN245("/mkt/bargain245"),
    BARGAIN246("/mkt/bargain246"),
    BARGAIN247("/mkt/bargain247"),
    BARGAIN248("/mkt/bargain248"),
    BARGAIN249("/mkt/bargain249"),
    BARGAIN250("/mkt/bargain250"),
    BARGAIN251("/mkt/bargain251"),
    BARGAIN252("/mkt/bargain252"),
    BARGAIN253("/mkt/bargain253"),
    BARGAIN254("/mkt/bargain254"),
    BARGAIN255("/mkt/bargain255"),
    BARGAIN256("/mkt/bargain256"),
    BARGAIN257("/mkt/bargain257"),
    BARGAIN258("/mkt/bargain258"),
    BARGAIN259("/mkt/bargain259"),
    BARGAIN260("/mkt/bargain260"),
    BARGAIN261("/mkt/bargain261"),
    BARGAIN262("/mkt/bargain262"),
    BARGAIN263("/mkt/bargain263"),
    BARGAIN264("/mkt/bargain264"),
    BARGAIN265("/mkt/bargain265"),
    BARGAIN266("/mkt/bargain266"),
    BARGAIN267("/mkt/bargain267"),
    BARGAIN268("/mkt/bargain268"),
    BARGAIN269("/mkt/bargain269"),
    BARGAIN270("/mkt/bargain270"),
    BARGAIN271("/mkt/bargain271"),
    BARGAIN272("/mkt/bargain272"),
    BARGAIN273("/mkt/bargain273"),
    BARGAIN274("/mkt/bargain274"),
    BARGAIN275("/mkt/bargain275"),
    BARGAIN276("/mkt/bargain276"),
    BARGAIN277("/mkt/bargain277"),
    BARGAIN278("/mkt/bargain278"),
    BARGAIN279("/mkt/bargain279"),
    BARGAIN280("/mkt/bargain280"),
    BARGAIN281("/mkt/bargain281"),
    BARGAIN282("/mkt/bargain282"),
    BARGAIN283("/mkt/bargain283"),
    BARGAIN284("/mkt/bargain284"),
    BARGAIN285("/mkt/bargain285"),
    BARGAIN286("/mkt/bargain286"),
    BARGAIN287("/mkt/bargain287"),
    BARGAIN288("/mkt/bargain288"),
    BARGAIN289("/mkt/bargain289"),
    BARGAIN290("/mkt/bargain290"),
    BARGAIN291("/mkt/bargain291"),
    BARGAIN292("/mkt/bargain292"),
    BARGAIN293("/mkt/bargain293"),
    BARGAIN294("/mkt/bargain294"),
    BARGAIN295("/mkt/bargain295"),
    BARGAIN296("/mkt/bargain296"),
    BARGAIN297("/mkt/bargain297"),
    BARGAIN298("/mkt/bargain298"),
    BARGAIN299("/mkt/bargain299"),
    BARGAIN300("/mkt/bargain300"),
    BARGAIN301("/mkt/bargain301"),
    BARGAIN302("/mkt/bargain302"),
    BARGAIN303("/mkt/bargain303"),
    BARGAIN304("/mkt/bargain304"),
    BARGAIN305("/mkt/bargain305"),
    BARGAIN306("/mkt/bargain306"),
    BARGAIN307("/mkt/bargain307"),
    BARGAIN308("/mkt/bargain308"),
    BARGAIN309("/mkt/bargain309"),
    BARGAIN310("/mkt/bargain310"),
    BARGAIN311("/mkt/bargain311"),
    BARGAIN312("/mkt/bargain312"),
    BARGAIN313("/mkt/bargain313"),
    BARGAIN314("/mkt/bargain314"),
    BARGAIN315("/mkt/bargain315"),
    BARGAIN316("/mkt/bargain316"),
    BARGAIN317("/mkt/bargain317"),
    BARGAIN318("/mkt/bargain318"),
    BARGAIN319("/mkt/bargain319"),
    BARGAIN320("/mkt/bargain320"),
    BARGAIN321("/mkt/bargain321"),
    BARGAIN322("/mkt/bargain322"),
    BARGAIN323("/mkt/bargain323"),
    BARGAIN324("/mkt/bargain324"),
    BARGAIN325("/mkt/bargain325"),
    BARGAIN326("/mkt/bargain326"),
    BARGAIN327("/mkt/bargain327"),
    BARGAIN328("/mkt/bargain328"),
    BARGAIN329("/mkt/bargain329"),
    BARGAIN330("/mkt/bargain330"),
    BARGAIN331("/mkt/bargain331"),
    BARGAIN332("/mkt/bargain332"),
    BARGAIN333("/mkt/bargain333"),
    BARGAIN334("/mkt/bargain334"),
    BARGAIN335("/mkt/bargain335"),
    BARGAIN336("/mkt/bargain336"),
    BARGAIN337("/mkt/bargain337"),
    BARGAIN338("/mkt/bargain338"),
    BARGAIN339("/mkt/bargain339"),
    BARGAIN340("/mkt/bargain340"),
    BARGAIN341("/mkt/bargain341"),
    BARGAIN342("/mkt/bargain342"),
    BARGAIN343("/mkt/bargain343"),
    BARGAIN344("/mkt/bargain344"),
    BARGAIN345("/mkt/bargain345"),
    BARGAIN346("/mkt/bargain346"),
    BARGAIN347("/mkt/bargain347"),
    BARGAIN348("/mkt/bargain348"),
    BARGAIN349("/mkt/bargain349"),
    BARGAIN350("/mkt/bargain350"),
    BARGAIN351("/mkt/bargain351"),
    BARGAIN352("/mkt/bargain352"),
    BARGAIN353("/mkt/bargain353"),
    BARGAIN354("/mkt/bargain354"),
    BARGAIN355("/mkt/bargain355"),
    BARGAIN356("/mkt/bargain356"),
    BARGAIN357("/mkt/bargain357"),
    BARGAIN358("/mkt/bargain358"),
    BARGAIN359("/mkt/bargain359"),
    BARGAIN360("/mkt/bargain360"),
    BARGAIN361("/mkt/bargain361"),
    BARGAIN362("/mkt/bargain362"),
    BARGAIN363("/mkt/bargain363"),
    BARGAIN364("/mkt/bargain364"),
    BARGAIN365("/mkt/bargain365"),
    BARGAIN366("/mkt/bargain366"),
    BARGAIN367("/mkt/bargain367"),
    BARGAIN368("/mkt/bargain368"),
    BARGAIN369("/mkt/bargain369"),
    BARGAIN370("/mkt/bargain370"),
    BARGAIN371("/mkt/bargain371"),
    BARGAIN372("/mkt/bargain372"),
    BARGAIN373("/mkt/bargain373"),
    BARGAIN374("/mkt/bargain374"),
    BARGAIN375("/mkt/bargain375"),
    BARGAIN376("/mkt/bargain376"),
    BARGAIN377("/mkt/bargain377"),
    BARGAIN378("/mkt/bargain378"),
    BARGAIN379("/mkt/bargain379"),
    BARGAIN380("/mkt/bargain380"),
    BARGAIN381("/mkt/bargain381"),
    BARGAIN382("/mkt/bargain382"),
    BARGAIN383("/mkt/bargain383"),
    BARGAIN384("/mkt/bargain384"),
    BARGAIN385("/mkt/bargain385"),
    BARGAIN386("/mkt/bargain386"),
    BARGAIN387("/mkt/bargain387"),
    BARGAIN388("/mkt/bargain388"),
    BARGAIN389("/mkt/bargain389"),
    BARGAIN390("/mkt/bargain390"),
    BARGAIN391("/mkt/bargain391"),
    BARGAIN392("/mkt/bargain392"),
    BARGAIN393("/mkt/bargain393"),
    BARGAIN394("/mkt/bargain394"),
    BARGAIN395("/mkt/bargain395"),
    BARGAIN396("/mkt/bargain396"),
    BARGAIN397("/mkt/bargain397"),
    BARGAIN398("/mkt/bargain398"),
    BARGAIN399("/mkt/bargain399"),
    BARGAIN400("/mkt/bargain400"),
    BARGAIN401("/mkt/bargain401"),
    BARGAIN402("/mkt/bargain402"),
    BARGAIN403("/mkt/bargain403"),
    BARGAIN404("/mkt/bargain404"),
    BARGAIN405("/mkt/bargain405"),
    BARGAIN406("/mkt/bargain406"),
    BARGAIN407("/mkt/bargain407"),
    BARGAIN408("/mkt/bargain408"),
    BARGAIN409("/mkt/bargain409"),
    BARGAIN410("/mkt/bargain410"),
    BARGAIN411("/mkt/bargain411"),
    BARGAIN412("/mkt/bargain412"),
    BARGAIN413("/mkt/bargain413"),
    BARGAIN414("/mkt/bargain414"),
    BARGAIN415("/mkt/bargain415"),
    BARGAIN416("/mkt/bargain416"),
    BARGAIN417("/mkt/bargain417"),
    BARGAIN418("/mkt/bargain418"),
    BARGAIN419("/mkt/bargain419"),
    BARGAIN420("/mkt/bargain420"),
    BARGAIN421("/mkt/bargain421"),
    BARGAIN422("/mkt/bargain422"),
    BARGAIN423("/mkt/bargain423"),
    BARGAIN424("/mkt/bargain424"),
    BARGAIN425("/mkt/bargain425"),
    BARGAIN426("/mkt/bargain426"),
    BARGAIN427("/mkt/bargain427"),
    BARGAIN428("/mkt/bargain428"),
    BARGAIN429("/mkt/bargain429"),
    BARGAIN430("/mkt/bargain430"),
    BARGAIN431("/mkt/bargain431"),
    BARGAIN432("/mkt/bargain432"),
    BARGAIN433("/mkt/bargain433"),
    BARGAIN434("/mkt/bargain434"),
    BARGAIN435("/mkt/bargain435"),
    BARGAIN436("/mkt/bargain436"),
    BARGAIN437("/mkt/bargain437"),
    BARGAIN438("/mkt/bargain438"),
    BARGAIN439("/mkt/bargain439"),
    BARGAIN440("/mkt/bargain440"),
    BARGAIN441("/mkt/bargain441"),
    BARGAIN442("/mkt/bargain442"),
    BARGAIN443("/mkt/bargain443"),
    BARGAIN444("/mkt/bargain444"),
    BARGAIN445("/mkt/bargain445"),
    BARGAIN446("/mkt/bargain446"),
    BARGAIN447("/mkt/bargain447"),
    BARGAIN448("/mkt/bargain448"),
    BARGAIN449("/mkt/bargain449"),
    BARGAIN450("/mkt/bargain450"),
    BARGAIN451("/mkt/bargain451"),
    BARGAIN452("/mkt/bargain452"),
    BARGAIN453("/mkt/bargain453"),
    BARGAIN454("/mkt/bargain454"),
    BARGAIN455("/mkt/bargain455"),
    BARGAIN456("/mkt/bargain456"),
    BARGAIN457("/mkt/bargain457"),
    BARGAIN458("/mkt/bargain458"),
    BARGAIN459("/mkt/bargain459"),
    BARGAIN460("/mkt/bargain460"),
    BARGAIN461("/mkt/bargain461"),
    BARGAIN462("/mkt/bargain462"),
    BARGAIN463("/mkt/bargain463"),
    BARGAIN464("/mkt/bargain464"),
    BARGAIN465("/mkt/bargain465"),
    BARGAIN466("/mkt/bargain466"),
    BARGAIN467("/mkt/bargain467"),
    BARGAIN468("/mkt/bargain468"),
    BARGAIN469("/mkt/bargain469"),
    BARGAIN470("/mkt/bargain470"),
    BARGAIN471("/mkt/bargain471"),
    BARGAIN472("/mkt/bargain472"),
    BARGAIN473("/mkt/bargain473"),
    BARGAIN474("/mkt/bargain474"),
    BARGAIN475("/mkt/bargain475"),
    BARGAIN476("/mkt/bargain476"),
    BARGAIN477("/mkt/bargain477"),
    BARGAIN478("/mkt/bargain478"),
    BARGAIN479("/mkt/bargain479"),
    BARGAIN480("/mkt/bargain480"),
    BARGAIN481("/mkt/bargain481"),
    BARGAIN482("/mkt/bargain482"),
    BARGAIN483("/mkt/bargain483"),
    BARGAIN484("/mkt/bargain484"),
    BARGAIN485("/mkt/bargain485"),
    BARGAIN486("/mkt/bargain486"),
    BARGAIN487("/mkt/bargain487"),
    BARGAIN488("/mkt/bargain488"),
    BARGAIN489("/mkt/bargain489"),
    BARGAIN490("/mkt/bargain490"),
    BARGAIN491("/mkt/bargain491"),
    BARGAIN492("/mkt/bargain492"),
    BARGAIN493("/mkt/bargain493"),
    BARGAIN494("/mkt/bargain494"),
    BARGAIN495("/mkt/bargain495"),
    BARGAIN496("/mkt/bargain496"),
    BARGAIN497("/mkt/bargain497"),
    BARGAIN498("/mkt/bargain498"),
    BARGAIN499("/mkt/bargain499"),
    BARGAIN500("/mkt/bargain500"),
    BARGAIN501("/mkt/bargain501"),
    BARGAIN502("/mkt/bargain502"),
    BARGAIN503("/mkt/bargain503"),
    BARGAIN504("/mkt/bargain504"),
    BARGAIN505("/mkt/bargain505"),
    BARGAIN506("/mkt/bargain506"),
    BARGAIN507("/mkt/bargain507"),
    BARGAIN508("/mkt/bargain508"),
    BARGAIN509("/mkt/bargain509"),
    BARGAIN510("/mkt/bargain510"),
    BARGAIN511("/mkt/bargain511"),
    BARGAIN512("/mkt/bargain512"),
    BARGAIN513("/mkt/bargain513"),
    BARGAIN514("/mkt/bargain514"),
    BARGAIN515("/mkt/bargain515"),
    BARGAIN516("/mkt/bargain516"),
    BARGAIN517("/mkt/bargain517"),
    BARGAIN518("/mkt/bargain518"),
    BARGAIN519("/mkt/bargain519"),
    BARGAIN520("/mkt/bargain520"),
    BARGAIN521("/mkt/bargain521"),
    BARGAIN522("/mkt/bargain522"),
    BARGAIN523("/mkt/bargain523"),
    BARGAIN524("/mkt/bargain524"),
    BARGAIN525("/mkt/bargain525"),
    BARGAIN526("/mkt/bargain526"),
    BARGAIN527("/mkt/bargain527"),
    BARGAIN528("/mkt/bargain528"),
    BARGAIN529("/mkt/bargain529"),
    BARGAIN530("/mkt/bargain530"),
    BARGAIN531("/mkt/bargain531"),
    BARGAIN532("/mkt/bargain532"),
    BARGAIN533("/mkt/bargain533"),
    BARGAIN534("/mkt/bargain534"),
    BARGAIN535("/mkt/bargain535"),
    BARGAIN536("/mkt/bargain536"),
    BARGAIN537("/mkt/bargain537"),
    BARGAIN538("/mkt/bargain538"),
    BARGAIN539("/mkt/bargain539"),
    BARGAIN540("/mkt/bargain540"),
    BARGAIN541("/mkt/bargain541"),
    BARGAIN542("/mkt/bargain542"),
    BARGAIN543("/mkt/bargain543"),
    BARGAIN544("/mkt/bargain544"),
    BARGAIN545("/mkt/bargain545"),
    BARGAIN546("/mkt/bargain546"),
    BARGAIN547("/mkt/bargain547"),
    BARGAIN548("/mkt/bargain548"),
    BARGAIN549("/mkt/bargain549"),
    BARGAIN550("/mkt/bargain550"),
    BARGAIN551("/mkt/bargain551"),
    BARGAIN552("/mkt/bargain552"),
    BARGAIN553("/mkt/bargain553"),
    BARGAIN554("/mkt/bargain554"),
    BARGAIN555("/mkt/bargain555"),
    BARGAIN556("/mkt/bargain556"),
    BARGAIN557("/mkt/bargain557"),
    BARGAIN558("/mkt/bargain558"),
    BARGAIN559("/mkt/bargain559"),
    BARGAIN560("/mkt/bargain560"),
    BARGAIN561("/mkt/bargain561"),
    BARGAIN562("/mkt/bargain562"),
    BARGAIN563("/mkt/bargain563"),
    BARGAIN564("/mkt/bargain564"),
    BARGAIN565("/mkt/bargain565"),
    BARGAIN566("/mkt/bargain566"),
    BARGAIN567("/mkt/bargain567"),
    BARGAIN568("/mkt/bargain568"),
    BARGAIN569("/mkt/bargain569"),
    BARGAIN570("/mkt/bargain570"),
    BARGAIN571("/mkt/bargain571"),
    BARGAIN572("/mkt/bargain572"),
    BARGAIN573("/mkt/bargain573"),
    BARGAIN574("/mkt/bargain574"),
    BARGAIN575("/mkt/bargain575"),
    BARGAIN576("/mkt/bargain576"),
    BARGAIN577("/mkt/bargain577"),
    BARGAIN578("/mkt/bargain578"),
    BARGAIN579("/mkt/bargain579"),
    BARGAIN580("/mkt/bargain580"),
    BARGAIN581("/mkt/bargain581"),
    BARGAIN582("/mkt/bargain582"),
    BARGAIN583("/mkt/bargain583"),
    BARGAIN584("/mkt/bargain584"),
    BARGAIN585("/mkt/bargain585"),
    BARGAIN586("/mkt/bargain586"),
    BARGAIN587("/mkt/bargain587"),
    BARGAIN588("/mkt/bargain588"),
    BARGAIN589("/mkt/bargain589"),
    BARGAIN590("/mkt/bargain590"),
    BARGAIN591("/mkt/bargain591"),
    BARGAIN592("/mkt/bargain592"),
    BARGAIN593("/mkt/bargain593"),
    BARGAIN594("/mkt/bargain594"),
    BARGAIN595("/mkt/bargain595"),
    BARGAIN596("/mkt/bargain596"),
    BARGAIN597("/mkt/bargain597"),
    BARGAIN598("/mkt/bargain598"),
    BARGAIN599("/mkt/bargain599"),
    BARGAIN600("/mkt/bargain600"),
    BARGAIN601("/mkt/bargain601"),
    BARGAIN602("/mkt/bargain602"),
    BARGAIN603("/mkt/bargain603"),
    BARGAIN604("/mkt/bargain604"),
    BARGAIN605("/mkt/bargain605"),
    BARGAIN606("/mkt/bargain606"),
    BARGAIN607("/mkt/bargain607"),
    BARGAIN608("/mkt/bargain608"),
    BARGAIN609("/mkt/bargain609"),
    BARGAIN610("/mkt/bargain610"),
    BARGAIN611("/mkt/bargain611"),
    BARGAIN612("/mkt/bargain612"),
    BARGAIN613("/mkt/bargain613"),
    BARGAIN614("/mkt/bargain614"),
    BARGAIN615("/mkt/bargain615"),
    BARGAIN616("/mkt/bargain616"),
    BARGAIN617("/mkt/bargain617"),
    BARGAIN618("/mkt/bargain618"),
    BARGAIN619("/mkt/bargain619"),
    BARGAIN620("/mkt/bargain620"),
    BARGAIN621("/mkt/bargain621"),
    BARGAIN622("/mkt/bargain622"),
    BARGAIN623("/mkt/bargain623"),
    BARGAIN624("/mkt/bargain624"),
    BARGAIN625("/mkt/bargain625"),
    BARGAIN626("/mkt/bargain626"),
    BARGAIN627("/mkt/bargain627"),
    BARGAIN628("/mkt/bargain628"),
    BARGAIN629("/mkt/bargain629"),
    BARGAIN630("/mkt/bargain630"),
    BARGAIN631("/mkt/bargain631"),
    BARGAIN632("/mkt/bargain632"),
    BARGAIN633("/mkt/bargain633"),
    BARGAIN634("/mkt/bargain634"),
    BARGAIN635("/mkt/bargain635"),
    BARGAIN636("/mkt/bargain636"),
    BARGAIN637("/mkt/bargain637"),
    BARGAIN638("/mkt/bargain638"),
    BARGAIN639("/mkt/bargain639"),
    BARGAIN640("/mkt/bargain640"),
    BARGAIN641("/mkt/bargain641"),
    BARGAIN642("/mkt/bargain642"),
    BARGAIN643("/mkt/bargain643"),
    BARGAIN644("/mkt/bargain644"),
    BARGAIN645("/mkt/bargain645"),
    BARGAIN646("/mkt/bargain646"),
    BARGAIN647("/mkt/bargain647"),
    BARGAIN648("/mkt/bargain648"),
    BARGAIN649("/mkt/bargain649"),
    BARGAIN650("/mkt/bargain650"),
    BARGAIN651("/mkt/bargain651"),
    BARGAIN652("/mkt/bargain652"),
    BARGAIN653("/mkt/bargain653"),
    BARGAIN654("/mkt/bargain654"),
    BARGAIN655("/mkt/bargain655"),
    BARGAIN656("/mkt/bargain656"),
    BARGAIN657("/mkt/bargain657"),
    BARGAIN658("/mkt/bargain658"),
    BARGAIN659("/mkt/bargain659"),
    BARGAIN660("/mkt/bargain660"),
    BARGAIN661("/mkt/bargain661"),
    BARGAIN662("/mkt/bargain662"),
    BARGAIN663("/mkt/bargain663"),
    BARGAIN664("/mkt/bargain664"),
    BARGAIN665("/mkt/bargain665"),
    BARGAIN666("/mkt/bargain666"),
    BARGAIN667("/mkt/bargain667"),
    BARGAIN668("/mkt/bargain668"),
    BARGAIN669("/mkt/bargain669"),
    BARGAIN670("/mkt/bargain670"),
    BARGAIN671("/mkt/bargain671"),
    BARGAIN672("/mkt/bargain672"),
    BARGAIN673("/mkt/bargain673"),
    BARGAIN674("/mkt/bargain674"),
    BARGAIN675("/mkt/bargain675"),
    BARGAIN676("/mkt/bargain676"),
    BARGAIN677("/mkt/bargain677"),
    BARGAIN678("/mkt/bargain678"),
    BARGAIN679("/mkt/bargain679"),
    BARGAIN680("/mkt/bargain680"),
    BARGAIN681("/mkt/bargain681"),
    BARGAIN682("/mkt/bargain682"),
    BARGAIN683("/mkt/bargain683"),
    BARGAIN684("/mkt/bargain684"),
    BARGAIN685("/mkt/bargain685"),
    BARGAIN686("/mkt/bargain686"),
    BARGAIN687("/mkt/bargain687"),
    BARGAIN688("/mkt/bargain688"),
    BARGAIN689("/mkt/bargain689"),
    BARGAIN690("/mkt/bargain690"),
    BARGAIN691("/mkt/bargain691"),
    BARGAIN692("/mkt/bargain692"),
    BARGAIN693("/mkt/bargain693"),
    BARGAIN694("/mkt/bargain694"),
    BARGAIN695("/mkt/bargain695"),
    BARGAIN696("/mkt/bargain696"),
    BARGAIN697("/mkt/bargain697"),
    BARGAIN698("/mkt/bargain698"),
    BARGAIN699("/mkt/bargain699"),
    BARGAIN700("/mkt/bargain700"),
    BARGAIN701("/mkt/bargain701"),
    BARGAIN702("/mkt/bargain702"),
    BARGAIN703("/mkt/bargain703"),
    BARGAIN704("/mkt/bargain704"),
    BARGAIN705("/mkt/bargain705"),
    BARGAIN706("/mkt/bargain706"),
    BARGAIN707("/mkt/bargain707"),
    BARGAIN708("/mkt/bargain708"),
    BARGAIN709("/mkt/bargain709"),
    BARGAIN710("/mkt/bargain710"),
    BARGAIN711("/mkt/bargain711"),
    BARGAIN712("/mkt/bargain712"),
    BARGAIN713("/mkt/bargain713"),
    BARGAIN714("/mkt/bargain714"),
    BARGAIN715("/mkt/bargain715"),
    BARGAIN716("/mkt/bargain716"),
    BARGAIN717("/mkt/bargain717"),
    BARGAIN718("/mkt/bargain718"),
    BARGAIN719("/mkt/bargain719"),
    BARGAIN720("/mkt/bargain720"),
    BARGAIN721("/mkt/bargain721"),
    BARGAIN722("/mkt/bargain722"),
    BARGAIN723("/mkt/bargain723"),
    BARGAIN724("/mkt/bargain724"),
    BARGAIN725("/mkt/bargain725"),
    BARGAIN726("/mkt/bargain726"),
    BARGAIN727("/mkt/bargain727"),
    BARGAIN728("/mkt/bargain728"),
    BARGAIN729("/mkt/bargain729"),
    BARGAIN730("/mkt/bargain730"),
    BARGAIN731("/mkt/bargain731"),
    BARGAIN732("/mkt/bargain732"),
    BARGAIN733("/mkt/bargain733"),
    BARGAIN734("/mkt/bargain734"),
    BARGAIN735("/mkt/bargain735"),
    BARGAIN736("/mkt/bargain736"),
    BARGAIN737("/mkt/bargain737"),
    BARGAIN738("/mkt/bargain738"),
    BARGAIN739("/mkt/bargain739"),
    BARGAIN740("/mkt/bargain740"),
    BARGAIN741("/mkt/bargain741"),
    BARGAIN742("/mkt/bargain742"),
    BARGAIN743("/mkt/bargain743"),
    BARGAIN744("/mkt/bargain744"),
    BARGAIN745("/mkt/bargain745"),
    BARGAIN746("/mkt/bargain746"),
    BARGAIN747("/mkt/bargain747"),
    BARGAIN748("/mkt/bargain748"),
    BARGAIN749("/mkt/bargain749"),
    BARGAIN750("/mkt/bargain750"),
    BARGAIN751("/mkt/bargain751"),
    BARGAIN752("/mkt/bargain752"),
    BARGAIN753("/mkt/bargain753"),
    BARGAIN754("/mkt/bargain754"),
    BARGAIN755("/mkt/bargain755"),
    BARGAIN756("/mkt/bargain756"),
    BARGAIN757("/mkt/bargain757"),
    BARGAIN758("/mkt/bargain758"),
    BARGAIN759("/mkt/bargain759"),
    BARGAIN760("/mkt/bargain760"),
    BARGAIN761("/mkt/bargain761"),
    BARGAIN762("/mkt/bargain762"),
    BARGAIN763("/mkt/bargain763"),
    BARGAIN764("/mkt/bargain764"),
    BARGAIN765("/mkt/bargain765"),
    BARGAIN766("/mkt/bargain766"),
    BARGAIN767("/mkt/bargain767"),
    BARGAIN768("/mkt/bargain768"),
    BARGAIN769("/mkt/bargain769"),
    BARGAIN770("/mkt/bargain770"),
    BARGAIN771("/mkt/bargain771"),
    BARGAIN772("/mkt/bargain772"),
    BARGAIN773("/mkt/bargain773"),
    BARGAIN774("/mkt/bargain774"),
    BARGAIN775("/mkt/bargain775"),
    BARGAIN776("/mkt/bargain776"),
    BARGAIN777("/mkt/bargain777"),
    BARGAIN778("/mkt/bargain778"),
    BARGAIN779("/mkt/bargain779"),
    BARGAIN780("/mkt/bargain780"),
    BARGAIN781("/mkt/bargain781"),
    BARGAIN782("/mkt/bargain782"),
    BARGAIN783("/mkt/bargain783"),
    BARGAIN784("/mkt/bargain784"),
    BARGAIN785("/mkt/bargain785"),
    BARGAIN786("/mkt/bargain786"),
    BARGAIN787("/mkt/bargain787"),
    BARGAIN788("/mkt/bargain788"),
    BARGAIN789("/mkt/bargain789"),
    BARGAIN790("/mkt/bargain790"),
    BARGAIN791("/mkt/bargain791"),
    BARGAIN792("/mkt/bargain792"),
    BARGAIN793("/mkt/bargain793"),
    BARGAIN794("/mkt/bargain794"),
    BARGAIN795("/mkt/bargain795"),
    BARGAIN796("/mkt/bargain796"),
    BARGAIN797("/mkt/bargain797"),
    BARGAIN798("/mkt/bargain798"),
    BARGAIN799("/mkt/bargain799"),
    BARGAIN800("/mkt/bargain800"),
    BARGAIN801("/mkt/bargain801"),
    BARGAIN802("/mkt/bargain802"),
    BARGAIN803("/mkt/bargain803"),
    BARGAIN804("/mkt/bargain804"),
    BARGAIN805("/mkt/bargain805"),
    BARGAIN806("/mkt/bargain806"),
    BARGAIN807("/mkt/bargain807"),
    BARGAIN808("/mkt/bargain808"),
    BARGAIN809("/mkt/bargain809"),
    BARGAIN810("/mkt/bargain810"),
    BARGAIN811("/mkt/bargain811"),
    BARGAIN812("/mkt/bargain812"),
    BARGAIN813("/mkt/bargain813"),
    BARGAIN814("/mkt/bargain814"),
    BARGAIN815("/mkt/bargain815"),
    BARGAIN816("/mkt/bargain816"),
    BARGAIN817("/mkt/bargain817"),
    BARGAIN818("/mkt/bargain818"),
    BARGAIN819("/mkt/bargain819"),
    BARGAIN820("/mkt/bargain820"),
    BARGAIN821("/mkt/bargain821"),
    BARGAIN822("/mkt/bargain822"),
    BARGAIN823("/mkt/bargain823"),
    BARGAIN824("/mkt/bargain824"),
    BARGAIN825("/mkt/bargain825"),
    BARGAIN826("/mkt/bargain826"),
    BARGAIN827("/mkt/bargain827"),
    BARGAIN828("/mkt/bargain828"),
    BARGAIN829("/mkt/bargain829"),
    BARGAIN830("/mkt/bargain830"),
    BARGAIN831("/mkt/bargain831"),
    BARGAIN832("/mkt/bargain832"),
    BARGAIN833("/mkt/bargain833"),
    BARGAIN834("/mkt/bargain834"),
    BARGAIN835("/mkt/bargain835"),
    BARGAIN836("/mkt/bargain836"),
    BARGAIN837("/mkt/bargain837"),
    BARGAIN838("/mkt/bargain838"),
    BARGAIN839("/mkt/bargain839"),
    BARGAIN840("/mkt/bargain840"),
    BARGAIN841("/mkt/bargain841"),
    BARGAIN842("/mkt/bargain842"),
    BARGAIN843("/mkt/bargain843"),
    BARGAIN844("/mkt/bargain844"),
    BARGAIN845("/mkt/bargain845"),
    BARGAIN846("/mkt/bargain846"),
    BARGAIN847("/mkt/bargain847"),
    BARGAIN848("/mkt/bargain848"),
    BARGAIN849("/mkt/bargain849"),
    BARGAIN850("/mkt/bargain850"),
    BARGAIN851("/mkt/bargain851"),
    BARGAIN852("/mkt/bargain852"),
    BARGAIN853("/mkt/bargain853"),
    BARGAIN854("/mkt/bargain854"),
    BARGAIN855("/mkt/bargain855"),
    BARGAIN856("/mkt/bargain856"),
    BARGAIN857("/mkt/bargain857"),
    BARGAIN858("/mkt/bargain858"),
    BARGAIN859("/mkt/bargain859"),
    BARGAIN860("/mkt/bargain860"),
    BARGAIN861("/mkt/bargain861"),
    BARGAIN862("/mkt/bargain862"),
    BARGAIN863("/mkt/bargain863"),
    BARGAIN864("/mkt/bargain864"),
    BARGAIN865("/mkt/bargain865"),
    BARGAIN866("/mkt/bargain866"),
    BARGAIN867("/mkt/bargain867"),
    BARGAIN868("/mkt/bargain868"),
    BARGAIN869("/mkt/bargain869"),
    BARGAIN870("/mkt/bargain870"),
    BARGAIN871("/mkt/bargain871"),
    BARGAIN872("/mkt/bargain872"),
    BARGAIN873("/mkt/bargain873"),
    BARGAIN874("/mkt/bargain874"),
    BARGAIN875("/mkt/bargain875"),
    BARGAIN876("/mkt/bargain876"),
    BARGAIN877("/mkt/bargain877"),
    BARGAIN878("/mkt/bargain878"),
    BARGAIN879("/mkt/bargain879"),
    BARGAIN880("/mkt/bargain880"),
    BARGAIN881("/mkt/bargain881"),
    BARGAIN882("/mkt/bargain882"),
    BARGAIN883("/mkt/bargain883"),
    BARGAIN884("/mkt/bargain884"),
    BARGAIN885("/mkt/bargain885"),
    BARGAIN886("/mkt/bargain886"),
    BARGAIN887("/mkt/bargain887"),
    BARGAIN888("/mkt/bargain888"),
    BARGAIN889("/mkt/bargain889"),
    BARGAIN890("/mkt/bargain890"),
    BARGAIN891("/mkt/bargain891"),
    BARGAIN892("/mkt/bargain892"),
    BARGAIN893("/mkt/bargain893"),
    BARGAIN894("/mkt/bargain894"),
    BARGAIN895("/mkt/bargain895"),
    BARGAIN896("/mkt/bargain896"),
    BARGAIN897("/mkt/bargain897"),
    BARGAIN898("/mkt/bargain898"),
    BARGAIN899("/mkt/bargain899"),
    BARGAIN900("/mkt/bargain900"),
    BARGAIN901("/mkt/bargain901"),
    BARGAIN902("/mkt/bargain902"),
    BARGAIN903("/mkt/bargain903"),
    BARGAIN904("/mkt/bargain904"),
    BARGAIN905("/mkt/bargain905"),
    BARGAIN906("/mkt/bargain906"),
    BARGAIN907("/mkt/bargain907"),
    BARGAIN908("/mkt/bargain908"),
    BARGAIN909("/mkt/bargain909"),
    BARGAIN910("/mkt/bargain910"),
    BARGAIN911("/mkt/bargain911"),
    BARGAIN912("/mkt/bargain912"),
    BARGAIN913("/mkt/bargain913"),
    BARGAIN914("/mkt/bargain914"),
    BARGAIN915("/mkt/bargain915"),
    BARGAIN916("/mkt/bargain916"),
    BARGAIN917("/mkt/bargain917"),
    BARGAIN918("/mkt/bargain918"),
    BARGAIN919("/mkt/bargain919"),
    BARGAIN920("/mkt/bargain920"),
    BARGAIN921("/mkt/bargain921"),
    BARGAIN922("/mkt/bargain922"),
    BARGAIN923("/mkt/bargain923"),
    BARGAIN924("/mkt/bargain924"),
    BARGAIN925("/mkt/bargain925"),
    BARGAIN926("/mkt/bargain926"),
    BARGAIN927("/mkt/bargain927"),
    BARGAIN928("/mkt/bargain928"),
    BARGAIN929("/mkt/bargain929"),
    BARGAIN930("/mkt/bargain930"),
    BARGAIN931("/mkt/bargain931"),
    BARGAIN932("/mkt/bargain932"),
    BARGAIN933("/mkt/bargain933"),
    BARGAIN934("/mkt/bargain934"),
    BARGAIN935("/mkt/bargain935"),
    BARGAIN936("/mkt/bargain936"),
    BARGAIN937("/mkt/bargain937"),
    BARGAIN938("/mkt/bargain938"),
    BARGAIN939("/mkt/bargain939"),
    BARGAIN940("/mkt/bargain940"),
    BARGAIN941("/mkt/bargain941"),
    BARGAIN942("/mkt/bargain942"),
    BARGAIN943("/mkt/bargain943"),
    BARGAIN944("/mkt/bargain944"),
    BARGAIN945("/mkt/bargain945"),
    BARGAIN946("/mkt/bargain946"),
    BARGAIN947("/mkt/bargain947"),
    BARGAIN948("/mkt/bargain948"),
    BARGAIN949("/mkt/bargain949"),
    BARGAIN950("/mkt/bargain950"),
    BARGAIN951("/mkt/bargain951"),
    BARGAIN952("/mkt/bargain952"),
    BARGAIN953("/mkt/bargain953"),
    BARGAIN954("/mkt/bargain954"),
    BARGAIN955("/mkt/bargain955"),
    BARGAIN956("/mkt/bargain956"),
    BARGAIN957("/mkt/bargain957"),
    BARGAIN958("/mkt/bargain958"),
    BARGAIN959("/mkt/bargain959"),
    BARGAIN960("/mkt/bargain960"),
    BARGAIN961("/mkt/bargain961"),
    BARGAIN962("/mkt/bargain962"),
    BARGAIN963("/mkt/bargain963"),
    BARGAIN964("/mkt/bargain964"),
    BARGAIN965("/mkt/bargain965"),
    BARGAIN966("/mkt/bargain966"),
    BARGAIN967("/mkt/bargain967"),
    BARGAIN968("/mkt/bargain968"),
    BARGAIN969("/mkt/bargain969"),
    BARGAIN970("/mkt/bargain970"),
    BARGAIN971("/mkt/bargain971"),
    BARGAIN972("/mkt/bargain972"),
    BARGAIN973("/mkt/bargain973"),
    BARGAIN974("/mkt/bargain974"),
    BARGAIN975("/mkt/bargain975"),
    BARGAIN976("/mkt/bargain976"),
    BARGAIN977("/mkt/bargain977"),
    BARGAIN978("/mkt/bargain978"),
    BARGAIN979("/mkt/bargain979"),
    BARGAIN980("/mkt/bargain980"),
    BARGAIN981("/mkt/bargain981"),
    BARGAIN982("/mkt/bargain982"),
    BARGAIN983("/mkt/bargain983"),
    BARGAIN984("/mkt/bargain984"),
    BARGAIN985("/mkt/bargain985"),
    BARGAIN986("/mkt/bargain986"),
    BARGAIN987("/mkt/bargain987"),
    BARGAIN988("/mkt/bargain988"),
    BARGAIN989("/mkt/bargain989"),
    BARGAIN990("/mkt/bargain990"),
    BARGAIN991("/mkt/bargain991"),
    BARGAIN992("/mkt/bargain992"),
    BARGAIN993("/mkt/bargain993"),
    BARGAIN994("/mkt/bargain994"),
    BARGAIN995("/mkt/bargain995"),
    BARGAIN996("/mkt/bargain996"),
    BARGAIN997("/mkt/bargain997"),
    BARGAIN998("/mkt/bargain998"),
    BARGAIN999("/mkt/bargain999"),
    ;

    private String path;

    SharePathEnums(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 判断枚举中是否包含该path
     * @param path pathUrl
     * @return 包含-是，不包含-否
     */
    public static boolean constantValue(String path){
        for (SharePathEnums pathEnums : values()){
            if (path.equals(pathEnums.path)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //List<String> stringList = Arrays.stream(SharePathEnums.values()).map(SharePathEnums::getPath).collect(Collectors.toList());
        Long start = System.currentTimeMillis();
        constantValue("/mkt/bargain998");
        constantValue("/mkt/bargain997");
        constantValue("/mkt/bargain996");
        constantValue("/mkt/bargain995");
        constantValue("/mkt/bargain994");
        constantValue("/mkt/bargain993");
        constantValue("/mkt/bargain992");
        constantValue("/mkt2/bargain991");
        constantValue("/mkt2/bargain898");
        constantValue("/mkt2/bargain897");
        Long end = System.currentTimeMillis();
        System.out.println("处理时间：" + (end - start));
    }
}